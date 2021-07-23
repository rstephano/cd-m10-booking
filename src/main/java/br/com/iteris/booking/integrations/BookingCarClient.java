package br.com.iteris.booking.integrations;

import br.com.iteris.booking.integrations.dto.request.Car;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "BookingCarClient", url = "http://localhost:82")
public interface BookingCarClient {

    @PostMapping(value = "/booking")
    void book(@RequestBody Car booking);

}
