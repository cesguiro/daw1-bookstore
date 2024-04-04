package es.cesguiro.daw1bookstore.common.exception;

public class AuthorizationException extends RuntimeException{

    public AuthorizationException(String message) {
        super("Authorization exception: " + message);
    }
}
