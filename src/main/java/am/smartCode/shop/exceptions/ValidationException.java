package am.smartCode.shop.exceptions;

public class ValidationException extends RuntimeException{
    public ValidationException(String message) {
        super(message);
    }
}