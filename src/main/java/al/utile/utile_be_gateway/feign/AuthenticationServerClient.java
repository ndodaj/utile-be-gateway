package al.utile.utile_be_gateway.feign;

import al.utile.utile_common.utile.AuthenticationRequest;
import al.utile.utile_common.utile.UtileServices;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@FeignClient(name = UtileServices.Constants.AUTHENTICATION_SERVER)
public interface AuthenticationServerClient {


    @PostMapping("/authenticate")
    String authenticate(@RequestBody AuthenticationRequest authenticationRequest);
}
