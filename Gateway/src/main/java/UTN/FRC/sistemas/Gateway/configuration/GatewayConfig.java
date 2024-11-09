package UTN.FRC.sistemas.Gateway.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class GatewayConfig {

    @Value("${notification-service.basic-path}")
    private String routeNotificationService;

    @Value("${tpi-service.basic.path}")
    private String routeTPIService;

    @Bean
    public RouteLocator configuringRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(p -> p.path("/api/v1/notification/**")
                        .filters(f -> f
                                .removeRequestHeader("X-Forwarded-For")
                                .removeRequestHeader("X-Forwarded-Proto")
                                .removeRequestHeader("X-Forwarded-Host")
                                .removeRequestHeader("X-Forwarded-Port"))
                        .uri(routeNotificationService))
                .route(p -> p.path("/api/v1/TPI/**")
                        .uri(routeTPIService))
                .build();
    }

    @Bean
    public WebClient.Builder webClientBuilder() {
        return WebClient.builder();
    }
}

