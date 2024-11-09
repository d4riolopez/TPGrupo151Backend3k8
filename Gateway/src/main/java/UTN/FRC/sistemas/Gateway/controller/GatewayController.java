package UTN.FRC.sistemas.Gateway.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import reactor.core.publisher.Mono;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/gateway")
public class GatewayController {

    @Autowired
    private WebClient.Builder webClientBuilder;

    @PostMapping("/sendNotification")
    public Mono<String> sendNotification(@RequestBody NotificationRequest request) {
        return ReactiveSecurityContextHolder.getContext()
                .map(context -> (UsernamePasswordAuthenticationToken) context.getAuthentication())
                .flatMap(auth -> webClientBuilder.build()
                        .post()
                        .uri("http://localhost:8082/api/v1/notification/employee-notification")
                        .header("Authorization", "Bearer " + extractJwtToken(auth))
                        .bodyValue(request)
                        .retrieve()
                        .bodyToMono(String.class));
    }

    private String extractJwtToken(UsernamePasswordAuthenticationToken authentication) {
        // Aquí puedes obtener el token JWT desde el objeto de autenticación,
        // dependiendo de cómo lo hayas almacenado. Por ejemplo, si lo has almacenado en el principal:
        return (String) authentication.getCredentials();
    }
}

