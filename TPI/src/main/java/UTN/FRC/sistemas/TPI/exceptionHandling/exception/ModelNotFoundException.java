package UTN.FRC.sistemas.TPI.exceptionHandling.exception;

public class ModelNotFoundException extends RuntimeException{
    public ModelNotFoundException(String message){
        super(message);
    }
}
