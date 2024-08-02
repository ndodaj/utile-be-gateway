package al.utile.utile_be_gateway.controller;

import al.utile.utile_be_gateway.security.JwtUtil;
import al.utile.utile_be_gateway.service.ServiceProvider;
import al.utile.utile_common.utile.dto.AuthenticationResponse;
import al.utile.utile_common.utile.dto.RoleDto;
import al.utile.utile_common.utile.service.UtileServices;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

import java.lang.reflect.ParameterizedType;
import java.util.List;

@RestController("api/roles")
public class RoleController {

    private final RestClient restClient;


    private final ServiceProvider serviceProvider;

    public RoleController(RestClient.Builder restClientBuilder, ServiceProvider serviceProvider) {
        restClient = restClientBuilder.build();
        this.serviceProvider = serviceProvider;
    }

    @GetMapping
    ResponseEntity<List<RoleDto>> getRoles() {
        ServiceInstance utileAppInstance = serviceProvider.serviceInstances.get(UtileServices.UTILE);

        List<RoleDto> body = restClient.get()
                .uri(utileAppInstance.getUri() + "/roles")
                .retrieve()
                .body(new ParameterizedTypeReference<>() {
                });
        return ResponseEntity.ok(body);
    }

}
