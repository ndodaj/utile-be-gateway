package al.utile.utile_be_gateway.controller;

import al.utile.utile_be_gateway.service.ServiceProvider;
import al.utile.utile_common.utile.dto.JobDto;
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
@RequestMapping("api/jobs")
@Tag(name = "Job Controller", description = "Job Management APIS")
public class JobController {

    private final RestClient restClient;

    private final ServiceProvider serviceProvider;

    public JobController(RestClient.Builder restClientBuilder, ServiceProvider serviceProvider) {
        restClient = restClientBuilder.build();
        this.serviceProvider = serviceProvider;
    }

    @GetMapping
    ResponseEntity<List<JobDto>> getJobs() {
        List<JobDto> body = restClient.get()
                .uri(utileURI())
                .retrieve()
                .body(new ParameterizedTypeReference<>() {
                });
        return ResponseEntity.ok(body);
    }

    @GetMapping("/{id}")
    public ResponseEntity<JobDto> getJob(@PathVariable("id") String id) {
        JobDto body = restClient.get()
                .uri(utileURI() + "/" + id)
                .retrieve()
                .body(new ParameterizedTypeReference<>() {
                });
        return ResponseEntity.ok(body);
    }

    @PostMapping
    public ResponseEntity<JobDto> createJob(@RequestBody JobDto job) {
        JobDto body = restClient.post()
                .uri(utileURI())
                .body(job)
                .retrieve()
                .body(new ParameterizedTypeReference<>() {
                });
        return ResponseEntity.ok(body);
    }

    @PutMapping("/{id}")
    public ResponseEntity<JobDto> updateJob(@PathVariable("id") String id, @RequestBody JobDto job) {
        JobDto body = restClient.put()
                .uri(utileURI() + "/" + id)
                .body(job)
                .retrieve()
                .body(new ParameterizedTypeReference<>() {
                });
        return ResponseEntity.ok(body);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteJob(@PathVariable("id") String id) {
        Boolean body = restClient.delete()
                .uri(utileURI() + "/" + id)
                .retrieve()
                .body(new ParameterizedTypeReference<>() {
                });
        return ResponseEntity.ok(body);
    }

    private String utileURI() {
        return serviceProvider.serviceInstances.get(UtileServices.UTILE).getUri() + "/jobs";
    }
}
