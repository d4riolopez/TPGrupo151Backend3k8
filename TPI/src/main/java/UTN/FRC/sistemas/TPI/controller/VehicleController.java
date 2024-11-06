package UTN.FRC.sistemas.TPI.controller;

import UTN.FRC.sistemas.TPI.model.dto.TestDto;
import UTN.FRC.sistemas.TPI.model.entities.Vehicle;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/TPI/vehicle")
public class VehicleController {
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
        ////completar
        //pruba tiene un autito, a ese autito le pedis la poscion,
        //aca crear posicion verifica si esta en un lugar no prohibido
        //si eta  en un lugar prohibido mandas la notificacion
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
