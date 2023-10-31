package web.development.services.exceptions;

public class ValidationException extends RuntimeException{
    public ValidationException() {
        super("Validation failed");
    }
}
