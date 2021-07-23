package br.com.iteris.booking.controller;

import br.com.iteris.booking.Paths;
import br.com.iteris.booking.dto.Booking;
import br.com.iteris.booking.dto.request.BookingRequest;
import br.com.iteris.booking.exceptions.InvalidCardException;
import br.com.iteris.booking.exceptions.InvalidTokenException;
import br.com.iteris.booking.integrations.AuthClient;
import br.com.iteris.booking.integrations.PaymentClient;
import br.com.iteris.booking.integrations.dto.request.Payment;
import br.com.iteris.booking.services.BookingService;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
public class BookingController {

    private final BookingService bookingService;

    private final AuthClient authClient;

    private final PaymentClient paymentClient;

    @PostMapping(path = Paths.BOOKING)
    public ResponseEntity<Void> book(@RequestHeader("token") String token, @RequestBody BookingRequest booking) {
        try {
            log.info("Checking authentication...");
            var auth = authClient.auth(token);
            log.info("User authenticated. {}", auth.toString());
            log.info("Processing payment...");
            var paymentResponse = paymentClient.processPayment( //
                Payment.builder() //
                    .value(booking.getValue()) //
                    .token(booking.getCardToken()) //
                    .build() //
            );
            log.info("Payment processed");
            bookingService.book( //
                Booking.builder() //
                    .paymentId(paymentResponse.getId()) //
                    .value(booking.getValue()) //
                    .hotel( //
                        Booking.Hotel.builder() //
                            .begin(booking.getHotel().getBegin()) //
                            .finish(booking.getHotel().getFinish()) //
                            .adults(booking.getHotel().getAdults()) //
                            .kids(booking.getHotel().getKids()) //
                            .build() //
                    ) //
                    .car( //
                        Booking.Car.builder() //
                            .begin(booking.getCar().getBegin()) //
                            .finish(booking.getCar().getFinish()) //
                            .build() //
                    ) //
                    .attraction( //
                        Booking.Attraction.builder() //
                            .name(booking.getAttraction().getName()) //
                            .date(booking.getAttraction().getDate()) //
                            .tickets(booking.getAttraction().getTickets()) //
                            .build() //
                    ) //
                    .build() //
            );
        } catch (FeignException e) {
            if (e.status() == 401) {
                log.error("Invalid auth token");
                throw new InvalidTokenException();
            } else if (e.status() == 402) {
                log.error("Invalid card");
                throw new InvalidCardException();
            }
        }
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
