package al.utile.utile_be_gateway.controller;

import al.utile.utile_be_gateway.service.AggregatorService;
import al.utile.utile_common.utile.dto.AggregatedDataDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AggregatorController {

    @Autowired
    private AggregatorService aggregatorService;

    @GetMapping("/aggregated-data")
    public AggregatedDataDto getAggregatedData(
            @RequestParam Long userId,
            @RequestParam Long professionalId) {
        return aggregatorService.getAggregatedData(userId, professionalId);
    }
}
