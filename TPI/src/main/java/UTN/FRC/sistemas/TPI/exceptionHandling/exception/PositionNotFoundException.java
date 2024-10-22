package UTN.FRC.sistemas.TPI.exceptionHandling.exception;

public class PositionNotFoundException extends RuntimeException{
    public PositionNotFoundException(String message){
        super(message);
    }
}
