package UTN.FRC.sistemas.Gateway.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/notification")
public class NotificationController {
    @PostMapping("/send")
    public ResponseEntity<String> sendNotification() {
        return ResponseEntity.ok("Notification sent"); } }
