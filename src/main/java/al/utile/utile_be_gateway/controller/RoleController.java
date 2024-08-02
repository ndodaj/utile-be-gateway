package al.utile.utile_be_gateway.controller;

import al.utile.utile_be_gateway.service.ServiceProvider;
import al.utile.utile_common.utile.dto.RoleDto;
import al.utile.utile_common.utile.service.UtileServices;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

import java.util.List;

@RestController
@RequestMapping("api/roles")
@Tag(name = "Role Controller", description = "Role APIs")
public class RoleController {

    private final RestClient restClient;


    private final ServiceProvider serviceProvider;

    public RoleController(RestClient.Builder restClientBuilder, ServiceProvider serviceProvider) {
        restClient = restClientBuilder.build();
        this.serviceProvider = serviceProvider;
    }

    @GetMapping
    ResponseEntity<List<RoleDto>> getRoles() {
        List<RoleDto> body = restClient.get()
                .uri(utileURI())
                .retrieve()
                .body(new ParameterizedTypeReference<>() {
                });
        return ResponseEntity.ok(body);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoleDto> getRole(@PathVariable("id") String id) {
        RoleDto body = restClient.get()
                .uri(utileURI() + "/" + id)
                .retrieve()
                .body(new ParameterizedTypeReference<>() {
                });
        return ResponseEntity.ok(body);
    }

    @PostMapping
    public ResponseEntity<RoleDto> createRole(@RequestBody RoleDto role) {
        RoleDto body = restClient.post()
                .uri(utileURI())
                .body(role)
                .retrieve()
                .body(new ParameterizedTypeReference<>() {
                });
        return ResponseEntity.ok(body);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RoleDto> updateRole(@PathVariable("id") String id, @RequestBody RoleDto role) {
        RoleDto body = restClient.put()
                .uri(utileURI() + "/" + id)
                .body(role)
                .retrieve()
                .body(new ParameterizedTypeReference<>() {
                });
        return ResponseEntity.ok(body);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteRole(@PathVariable("id") String id) {
        Boolean body = restClient.delete()
                .uri(utileURI() + "/" + id)
                .retrieve()
                .body(new ParameterizedTypeReference<>() {
                });
        return ResponseEntity.ok(body);
    }

    private String utileURI() {
        return serviceProvider.serviceInstances.get(UtileServices.UTILE) + "/roles";
    }

}
