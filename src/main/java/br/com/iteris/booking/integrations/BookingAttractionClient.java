package br.com.iteris.booking.integrations;

import br.com.iteris.booking.integrations.dto.request.Attraction;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "BookingAttractionClient", url = "http://localhost:83")
public interface BookingAttractionClient {

    @PostMapping(value = "/booking")
    void book(@RequestBody Attraction booking);

}
