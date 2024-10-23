package UTN.FRC.sistemas.TPI.exceptionHandling.exception.Vehicle;

public class VehicleAlreadyTestingException extends RuntimeException{
    public VehicleAlreadyTestingException(String message){
        super(message);
    }
}
