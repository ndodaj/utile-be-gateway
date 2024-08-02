package al.utile.utile_be_gateway.feign;

import al.utile.utile_common.utile.dto.AuthenticationRequest;
import al.utile.utile_common.utile.dto.AuthenticationResponse;
import al.utile.utile_common.utile.service.UtileServices;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@FeignClient(name = UtileServices.Constants.UTILE_MAIN)
public interface AuthenticationServerClient {


    @PostMapping("/authenticate")
    AuthenticationResponse authenticate(@RequestBody AuthenticationRequest authenticationRequest);
}
