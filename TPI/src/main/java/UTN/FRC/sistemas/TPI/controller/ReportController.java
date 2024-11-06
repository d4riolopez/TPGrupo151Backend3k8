package UTN.FRC.sistemas.TPI.controller;

import UTN.FRC.sistemas.TPI.model.entities.Employee;
import UTN.FRC.sistemas.TPI.model.entities.Test;
import UTN.FRC.sistemas.TPI.service.EmployeeService;
import UTN.FRC.sistemas.TPI.service.TestService;
import UTN.FRC.sistemas.TPI.service.VehicleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RequestMapping("/api/v1/TPI/report/")
@RestController
@RequiredArgsConstructor
public class ReportController {
    private final TestService testService;
    private final EmployeeService employeeService;
    private final VehicleService vehicleService;
    //f. Generar reportes de:
    //i. Incidentes (pruebas donde se excedieron los límites establecidos)
        //pruebas donde se violaron las reglas del negocio(agregar campo a test de  incidente(boolean)
        // si el test surgio algun inconveniente)

    @GetMapping("/incident")
    public ResponseEntity<?> getAllIncidents(){
        List<Test> incidents = testService.getAllIncidentTests();
        if(!incidents.isEmpty()){
            return ResponseEntity.ok(incidents);
        }
        return new ResponseEntity<>("There isn't any incident to show", HttpStatus.NO_CONTENT);

    }

    //ii. Detalle de incidentes para un empleado
        //incidentes por empleado(combina el anterior ademas del empleado)
    @GetMapping("/incident-by-employee/{employeeId}")
    public ResponseEntity<?> getIncidentsByEmployee(@PathVariable Long employeeId){
        List<Test> incidentByEmployee = employeeService.findById(employeeId).getIncidents();
        if(!incidentByEmployee.isEmpty()){
            return ResponseEntity.ok(incidentByEmployee);
        }else{
            return new ResponseEntity<>("There isn't incidents for the employee", HttpStatus.NO_CONTENT);
        }

    }
    //iii. Cantidad de kilómetros de prueba que recorrió un vehículo en un
    //período determinado.
        //km recorridos de un vehiculo en un periodo dado
    //iv. Detalle de pruebas realizadas para un vehículo
        //pruebas por vehiculo
    @GetMapping("/test-by-vehicle/{vehicleId}")
    public ResponseEntity<?> getTestByVehicleId(@PathVariable Long vehicleId ){
        Set<Test> testsByVehicle = vehicleService.findById(vehicleId).getTests();
        if(!testsByVehicle.isEmpty()){
            return ResponseEntity.ok(testsByVehicle);
        }else{
            return new ResponseEntity<>("There isn't tests for the current vehicle", HttpStatus.NO_CONTENT);
        }
    }
}
