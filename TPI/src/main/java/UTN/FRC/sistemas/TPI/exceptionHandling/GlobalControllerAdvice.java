package UTN.FRC.sistemas.TPI.exceptionHandling;

import UTN.FRC.sistemas.TPI.exceptionHandling.exception.Interested.InterestedIsRestrictedException;
import UTN.FRC.sistemas.TPI.exceptionHandling.exception.Interested.InterestedLicenseExpiredException;
import UTN.FRC.sistemas.TPI.exceptionHandling.exception.Interested.InterestedNotFoundException;
import UTN.FRC.sistemas.TPI.exceptionHandling.exception.Vehicle.VehicleAlreadyTestingException;
import UTN.FRC.sistemas.TPI.exceptionHandling.exception.Vehicle.VehicleNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalControllerAdvice {

    //------------------------Interested Exceptions
    @ExceptionHandler(InterestedNotFoundException.class)
    public ResponseEntity<?> interestedNotFound(InterestedNotFoundException INFE){
        return new ResponseEntity<>(INFE.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InterestedIsRestrictedException.class)
    public ResponseEntity<?> interestedRestricted(InterestedIsRestrictedException IRE){
        return new ResponseEntity<>(IRE.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(InterestedLicenseExpiredException.class)
    public ResponseEntity<?> interestedLicenceExpired(InterestedLicenseExpiredException IELE){
        return new ResponseEntity<>(IELE.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
    }

    //------------------------Vehicle Exceptions
    @ExceptionHandler(VehicleAlreadyTestingException.class)
    public ResponseEntity<?> vehicleIsOnTesting(VehicleAlreadyTestingException VAOTE){
        return new ResponseEntity<>(VAOTE.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(VehicleNotFoundException.class)
    public ResponseEntity<?> vehicleNotFound(VehicleNotFoundException VNE){
        return new ResponseEntity<>(VNE.getMessage(), HttpStatus.NOT_FOUND);
    }


}
