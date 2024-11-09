package UTN.FRC.sistemas.Gateway.controller;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/reports")
public class ReportsController {
    @GetMapping("/view")
    public ResponseEntity<String> viewReports() {
        return ResponseEntity.ok("Viewing reports"); } }