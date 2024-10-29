package UTN.FRC.sistemas.Gateway.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Value("${notification-service.basic-path}")
    private String routeNotificationService;

    @Value("${tpi-service.basic.path}")
    private String routeTPIService;

    @Bean
    public RouteLocator configuringRoutes(RouteLocatorBuilder builder){
        return builder.routes()
                .route(p -> p.path("/api/v1/notification/**")
                        .uri(routeNotificationService))
                .route(p -> p.path("/api/v1/TPI/**")
                        .uri(routeTPIService))
                .build();
    }
}