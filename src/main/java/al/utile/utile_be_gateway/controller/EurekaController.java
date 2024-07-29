package al.utile.utile_be_gateway.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

@Slf4j
@RestController
public class EurekaController {

    private final DiscoveryClient discoveryClient;
    private final RestClient restClient;

    public EurekaController(DiscoveryClient discoveryClient, RestClient.Builder restClientBuilder) {
        this.discoveryClient = discoveryClient;
        restClient = restClientBuilder.build();
    }

    @GetMapping("helloEureka")
    public String helloWorld() {
        log.info("services {}", discoveryClient.getServices().toString());
        ServiceInstance serviceInstance = discoveryClient.getInstances("utile-app").get(0);
        return restClient.get()
                .uri(serviceInstance.getUri() + "/utile-test")
                .retrieve()
                .body(String.class);
    }
}
