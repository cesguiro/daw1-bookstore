package es.cesguiro.daw1bookstore.common.exception;

public class AuthException extends RuntimeException{

    public AuthException(String message) {
        super("Auth exception: " + message);
    }
}
