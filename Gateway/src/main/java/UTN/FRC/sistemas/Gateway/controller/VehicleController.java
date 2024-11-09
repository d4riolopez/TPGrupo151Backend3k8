package UTN.FRC.sistemas.Gateway.controller;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/api/v1/vehicle")
public class VehicleController {
    @PostMapping("/sendPosition")
    public ResponseEntity<String> sendPosition() {
        return ResponseEntity.ok("Position sent"); } }