package br.com.iteris.booking.integrations;

import br.com.iteris.booking.integrations.dto.response.Auth;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "AuthClient", url = "http://localhost:8080")
public interface AuthClient {

    @GetMapping(value = "/auth/{token}")
    Auth auth(@PathVariable("token") String token);

}
