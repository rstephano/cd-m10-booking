package br.com.iteris.booking.integrations;

import br.com.iteris.booking.integrations.dto.request.Payment;
import br.com.iteris.booking.integrations.dto.response.PaymentResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "PaymentClient", url = "http://localhost:8081")
public interface PaymentClient {

    @GetMapping(value = "/payment")
    PaymentResponse processPayment(@RequestBody Payment payment);

}
