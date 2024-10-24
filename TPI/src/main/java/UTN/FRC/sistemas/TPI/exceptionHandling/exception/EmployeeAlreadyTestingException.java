package UTN.FRC.sistemas.TPI.exceptionHandling.exception;

public class EmployeeAlreadyTestingException extends RuntimeException{
    public EmployeeAlreadyTestingException(String message){
        super(message);
    }
}
