package es.cesguiro.daw1bookstore.common.exception;

public class AuthenticateException extends RuntimeException{

    public AuthenticateException(String message) {
        super("Authenticate exception: " + message);
    }
}
