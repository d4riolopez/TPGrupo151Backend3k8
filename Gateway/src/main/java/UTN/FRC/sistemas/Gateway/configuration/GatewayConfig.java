package UTN.FRC.sistemas.Gateway.configuration;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@Getter
public class GatewayConfig {
    @Value("${notification-service.basic-path}")
    private String routeNotificationService;

    @Value("${tpi-service.basic-path}")
    private String routeTPIService;

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
