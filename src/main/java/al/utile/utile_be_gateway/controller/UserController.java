package al.utile.utile_be_gateway.controller;

import al.utile.utile_be_gateway.service.ServiceProvider;
import al.utile.utile_common.utile.dto.UserDto;
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
@RequestMapping("api/users")
@Tag(name = "User Controller", description = "User Management APIS")
public class UserController {

    private final RestClient restClient;

    private final ServiceProvider serviceProvider;

    public UserController(RestClient.Builder restClientBuilder, ServiceProvider serviceProvider) {
        restClient = restClientBuilder.build();
        this.serviceProvider = serviceProvider;
    }

    @GetMapping
    ResponseEntity<List<UserDto>> getUsers() {
        List<UserDto> body = restClient.get()
                .uri(utileURI())
                .retrieve()
                .body(new ParameterizedTypeReference<>() {
                });
        return ResponseEntity.ok(body);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable("id") String id) {
        UserDto body = restClient.get()
                .uri(utileURI() + "/" + id)
                .retrieve()
                .body(new ParameterizedTypeReference<>() {
                });
        return ResponseEntity.ok(body);
    }

    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto user) {
        UserDto body = restClient.post()
                .uri(utileURI())
                .body(user)
                .retrieve()
                .body(new ParameterizedTypeReference<>() {
                });
        return ResponseEntity.ok(body);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable("id") String id, @RequestBody UserDto user) {
        UserDto body = restClient.put()
                .uri(utileURI() + "/" + id)
                .body(user)
                .retrieve()
                .body(new ParameterizedTypeReference<>() {
                });
        return ResponseEntity.ok(body);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteUser(@PathVariable("id") String id) {
        Boolean body = restClient.delete()
                .uri(utileURI() + "/" + id)
                .retrieve()
                .body(new ParameterizedTypeReference<>() {
                });
        return ResponseEntity.ok(body);
    }

    private String utileURI() {
        return serviceProvider.serviceInstances.get(UtileServices.UTILE).getUri() + "/users";
    }
}
