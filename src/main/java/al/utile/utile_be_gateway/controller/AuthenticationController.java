package al.utile.utile_be_gateway.controller;

import al.utile.utile_be_gateway.security.JwtUtil;
import al.utile.utile_be_gateway.service.ServiceProvider;
import al.utile.utile_common.utile.dto.AuthenticationRequest;
import al.utile.utile_common.utile.dto.AuthenticationResponse;
import al.utile.utile_common.utile.service.UtileServices;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

@Slf4j
@RestController
public class AuthenticationController {


    private final RestClient restClient;

    private final JwtUtil jwtUtil;

    private final ServiceProvider serviceProvider;


    public AuthenticationController(RestClient.Builder restClientBuilder, JwtUtil jwtUtil, ServiceProvider serviceProvider) {
        restClient = restClientBuilder.build();
        this.jwtUtil = jwtUtil;
        this.serviceProvider = serviceProvider;
    }

    @SecurityRequirements()
    @PostMapping("/authenticate")
    public String createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) {

        ServiceInstance utileAppInstance = serviceProvider.serviceInstances.get(UtileServices.UTILE);

        AuthenticationResponse response = restClient.post()
                .uri(utileAppInstance.getUri() + "/authenticate")
                .contentType(MediaType.APPLICATION_JSON)
                .body(authenticationRequest)
                .retrieve()
                .body(AuthenticationResponse.class);

        assert response != null;
        return jwtUtil.generateToken(response.username(), response.roles());
    }


}
