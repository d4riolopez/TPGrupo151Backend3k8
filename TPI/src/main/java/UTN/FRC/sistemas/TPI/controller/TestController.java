package UTN.FRC.sistemas.TPI.controller;

import UTN.FRC.sistemas.TPI.model.entities.Test;
import UTN.FRC.sistemas.TPI.service.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/vi-1/test")
@RestController
@RequiredArgsConstructor
public class TestController {
    private final TestService testService;
//validation applied on test service
    @PostMapping
    public ResponseEntity<?> createTest(@RequestBody Test test){
        testService.create(test);
        return ResponseEntity.ok("Test Successfully created");
    }
}
