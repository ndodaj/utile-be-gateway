package al.utile.utile_be_gateway.service;

import al.utile.utile_be_gateway.feign.ProfessionalClient;
import al.utile.utile_be_gateway.feign.UtileClient;
import al.utile.utile_common.utile.dto.AggregatedDataDto;
import al.utile.utile_common.utile.dto.ProfessionalDto;
import al.utile.utile_common.utile.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AggregatorService {

    private final UtileClient utileClient;

    private final ProfessionalClient professionalClient;

    public AggregatorService(UtileClient utileClient, ProfessionalClient professionalClient) {
        this.utileClient = utileClient;
        this.professionalClient = professionalClient;
    }

    public AggregatedDataDto getAggregatedData(Long userId, Long professionalId) {
        UserDto user = utileClient.getUser(userId);
        ProfessionalDto professional = professionalClient.getProfessional(professionalId);

        return new AggregatedDataDto(user, professional);
    }
}
