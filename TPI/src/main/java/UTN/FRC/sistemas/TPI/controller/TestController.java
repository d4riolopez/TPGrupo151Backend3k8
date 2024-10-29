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

@RequestMapping("/api/v1/TPI/tests")
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

    //Recibir la posición actual de un vehículo y evaluar si el vehículo se encuentra
    //en una prueba para revisar si está dentro de los límites establecidos. En caso
    //de que el vehículo se encuentre en una prueba y haya excedido el radio
    //permitido o ingresado a una zona peligrosa, se deben disparar las acciones
    //descriptas. ATENCIÓN: NO se espera que los alumnos hagan una
    //notificación real a un teléfono, sino que alcanza con almacenar la notificación
    //en la base de datos; pero si un grupo desea investigar e implementar una
    //notificación por mail, SMS, WhatsApp o cualquier medio, tiene libertad para
    //hacerlo.

    //if isTooFar || inRestrictedArea ->
    //notify Employee
    //client setRestricted==true
    @GetMapping("/positon")
    public ResponseEntity<?> getCurrentPosition(@Valid @RequestBody TestDto dto) {
        Test test = mapper.toEntity(dto);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
