package br.com.iteris.booking.integrations;

import br.com.iteris.booking.integrations.dto.request.Hotel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "BookingHotelClient", url = "http://localhost:81")
public interface BookingHotelClient {

    @PostMapping(value = "/booking")
    void book(@RequestBody Hotel booking);

}
