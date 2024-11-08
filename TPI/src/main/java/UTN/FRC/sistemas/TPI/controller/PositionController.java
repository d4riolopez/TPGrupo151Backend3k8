package UTN.FRC.sistemas.TPI.controller;

import UTN.FRC.sistemas.TPI.mapper.TestMapper;
import UTN.FRC.sistemas.TPI.model.dto.TestDto;
import UTN.FRC.sistemas.TPI.model.entities.Position;
import UTN.FRC.sistemas.TPI.model.entities.Test;
import UTN.FRC.sistemas.TPI.service.PositionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/TPI/position")
@RequiredArgsConstructor
public class PositionController {
    private final TestMapper mapper;
    private final PositionService positionService;

    //Recibir la posición actual de un vehículo y evaluar si el vehículo se encuentra
    //en una prueba para revisar si está dentro de los límites establecidos. En caso
    //de que el vehículo se encuentre en una prueba y haya excedido el radio
    //permitido o ingresado a una zona peligrosa, se deben disparar las acciones
    //descriptas. ATENCIÓN: NO se espera que los alumnos hagan una
    //notificación real a un teléfono, sino que alcanza con almacenar la notificación
    //en la base de datos; pero si un grupo desea investigar e implementar una
    //notificación por mail, SMS, WhatsApp o cualquier medio, tiene libertad para
    //hacerlo.
    @GetMapping("/current/{latitude}/{length}")
    public ResponseEntity<?> getCurrentPosition(@Valid @RequestBody TestDto dto,
                                                @PathVariable Long latitude,
                                                @PathVariable Long length) {
        Test test = mapper.toEntity(dto);
        Position position = positionService.calculatePosition(test, latitude, length);

        return new ResponseEntity<>(position, HttpStatus.OK);
    }

    @GetMapping("/")
    public String testing1d(){
        positionService.validatePosition((long)1.3232,(long)2.23);
        return "tested";
    }

}
