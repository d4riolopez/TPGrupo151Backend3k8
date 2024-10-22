package UTN.FRC.sistemas.TPI.exceptionHandling.exception;

public class VehicleNotFoundException extends RuntimeException{
    public VehicleNotFoundException(String message){
        super(message);
    }
}
