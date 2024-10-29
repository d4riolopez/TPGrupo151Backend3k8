package UTN.FRC.sistemas.TPI.controller;

import UTN.FRC.sistemas.TPI.model.entities.Test;
import UTN.FRC.sistemas.TPI.service.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/api/v1/TPI/report/")
@RestController
@RequiredArgsConstructor
public class ReportController {
    private final TestService testService;
    //f. Generar reportes de:
    //i. Incidentes (pruebas donde se excedieron los límites establecidos)
    @GetMapping("/incident")
    public ResponseEntity<?> getAllIncidents(){
        List<Test> incidents = testService.getAllIncidentTests();
        if(!incidents.isEmpty()){
            return ResponseEntity.ok(incidents);
        }
        return new ResponseEntity<>("There isn't any incident to show", HttpStatus.NO_CONTENT);

    }

        //pruebas donde se violaron las reglas del negocio(agregar campo a test de  incidente(boolean) si el test surgio algun inconveniente)
    //ii. Detalle de incidentes para un empleado
        //incidentes por empleado(combina el anterior ademas del empleado)
    //iii. Cantidad de kilómetros de prueba que recorrió un vehículo en un
    //período determinado.
        //km recorridos de un vehiculo en un periodo dado
    //iv. Detalle de pruebas realizadas para un vehículo
        //pruebas por vehiculo
}
