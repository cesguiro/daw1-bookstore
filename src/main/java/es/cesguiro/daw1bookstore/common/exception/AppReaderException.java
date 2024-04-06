package es.cesguiro.daw1bookstore.common.exception;

public class AppReaderException extends RuntimeException{

    public AppReaderException(String message) {
        super("AppReader exception: " + message);
    }
}
