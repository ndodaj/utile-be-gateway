package al.utile.utile_be_gateway.feign;

import al.utile.utile_common.utile.dto.ProfessionalDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "professional-service")
public interface ProfessionalClient {

    @GetMapping("/professionals/{id}")
    ProfessionalDto getProfessional(@PathVariable("id") Long id);
}
