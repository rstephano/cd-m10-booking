package br.com.iteris.booking.services;

import br.com.iteris.booking.dto.Booking;
import br.com.iteris.booking.integrations.BookingAttractionClient;
import br.com.iteris.booking.integrations.BookingCarClient;
import br.com.iteris.booking.integrations.BookingHotelClient;
import br.com.iteris.booking.integrations.dto.request.Attraction;
import br.com.iteris.booking.integrations.dto.request.Car;
import br.com.iteris.booking.integrations.dto.request.Hotel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class BookingService {

    private final BookingHotelClient bookingHotelClient;

    private final BookingCarClient bookingCarClient;

    private final BookingAttractionClient bookingAttractionClient;

    public void book(Booking booking) {
        log.info("Booking. {}", booking.toString());
        log.info("Booking Hotel...");
        bookingHotelClient.book( //
            Hotel.builder() //
                .paymentId(booking.getPaymentId()) //
                .begin(booking.getHotel().getBegin()) //
                .finish(booking.getHotel().getFinish()) //
                .adults(booking.getHotel().getAdults()) //
                .kids(booking.getHotel().getKids()) //
                .build() //
        );
        log.info("Booking Car...");
        bookingCarClient.book( //
            Car.builder()  //
                .paymentId(booking.getPaymentId()) //
                .begin(booking.getCar().getBegin()) //
                .finish(booking.getCar().getFinish()) //
                .build() //
        );
        log.info("Booking Attraction...");
        bookingAttractionClient.book( //
            Attraction.builder()  //
                .paymentId(booking.getPaymentId()) //
                .name(booking.getAttraction().getName()) //
                .date(booking.getAttraction().getDate()) //
                .tickets(booking.getAttraction().getTickets()) //
                .build() //
        );
        log.info("Reservations successfully processed");
    }

}
