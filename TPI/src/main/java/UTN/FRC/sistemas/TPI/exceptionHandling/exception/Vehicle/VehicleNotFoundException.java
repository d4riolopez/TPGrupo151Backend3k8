package UTN.FRC.sistemas.TPI.exceptionHandling.exception.Vehicle;

public class VehicleNotFoundException extends RuntimeException{
    public VehicleNotFoundException(String message){
        super(message);
    }
}
