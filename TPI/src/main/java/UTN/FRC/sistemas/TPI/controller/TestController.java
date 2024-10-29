package UTN.FRC.sistemas.TPI.controller;

import UTN.FRC.sistemas.TPI.mapper.TestMapper;
import UTN.FRC.sistemas.TPI.model.dto.TestDto;
import UTN.FRC.sistemas.TPI.model.entities.Test;
import UTN.FRC.sistemas.TPI.service.TestService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/TPI/test")
@RestController
@RequiredArgsConstructor
public class TestController {
    private final TestService testService;
    private final TestMapper mapper;

    //validation applied on test service
    @PostMapping("/")
    public ResponseEntity<?> createTest(@Valid @RequestBody TestDto dto) {
        testService.create(mapper.toEntity(dto));
        return ResponseEntity.ok("Test Successfully created");
    }

    // Listar todas las pruebas en curso en un momento dado
    @GetMapping("/")
    public ResponseEntity<?> getOngoingTests() {
        List<Test> tests = testService.getOngoingTests();
        return new ResponseEntity<>(tests, HttpStatus.OK);
    }

    //Finalizar una prueba, permitiéndole al empleado agregar un comentario
    //sobre la misma
    @PatchMapping("/")
    public ResponseEntity<?> finishTest(@Valid @RequestBody TestDto dto) {
        testService.finishTest(mapper.toEntity(dto));
        return new ResponseEntity<>("Test finished", HttpStatus.OK);
    }


}
