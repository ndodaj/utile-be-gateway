package al.utile.utile_be_gateway.service;

import al.utile.utile_common.utile.service.UtileServices;
import lombok.Getter;
import lombok.Setter;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Component;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

@Component
@Getter
@Setter
public class ServiceProvider {

    public final Map<UtileServices, ServiceInstance> serviceInstances;

    private final DiscoveryClient discoveryClient;

    public ServiceProvider(DiscoveryClient discoveryClient) {
        this.discoveryClient = discoveryClient;
        serviceInstances = new EnumMap<>(UtileServices.class);

        List<ServiceInstance> utile = discoveryClient.getInstances(UtileServices.UTILE.getServiceName());
        if(!utile.isEmpty()) serviceInstances.putIfAbsent(UtileServices.UTILE, utile.get(0));

        List<ServiceInstance> professional = discoveryClient.getInstances(UtileServices.PROFESSIONAL.getServiceName());
        if(!professional.isEmpty()) serviceInstances.putIfAbsent(UtileServices.PROFESSIONAL, professional.get(0));

    }

}
