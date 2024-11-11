package UTN.FRC.sistemas.Gateway.controller;

import UTN.FRC.sistemas.Gateway.configuration.GatewayConfig;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class GatewayController {
    private final RestTemplate restTemplate;
    private final GatewayConfig config;

    // Ruta para el servicio de notificaciones
    @RequestMapping(value = "/notification/**", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PATCH, RequestMethod.PUT})
    public ResponseEntity<?> redirectNotificationService(
            //@PathVariable("path") String path,
            @RequestBody(required = false) String body, HttpMethod method, HttpServletRequest request) {
        String path = request.getRequestURI().split("/notification/")[1];

        String url = config.getRouteNotificationService() + "/api/v1/notification/" + path;
        System.out.println("url: " + url + " method:" + method.name());
        HttpEntity<String> entity = new HttpEntity<>(body);
        return restTemplate.exchange(url, method, entity, String.class);
    }

    // Ruta para el servicio TPI
    @RequestMapping(value = "/TPI/**", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PATCH, RequestMethod.PUT})
    public ResponseEntity<?> redirectTPIService(
            //@PathVariable("path") String path,
            @RequestBody(required = false) String body, HttpMethod method, HttpServletRequest request) {
        String path = request.getRequestURI().split("/TPI/")[1];

        String url = config.getRouteTPIService() + "/api/v1/TPI/" + path;
        System.out.println("url: " + url + " method:" + method.name());

        HttpEntity<String> entity = new HttpEntity<>(body);
        return restTemplate.exchange(url, method, entity, String.class);
    }
}
