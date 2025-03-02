package es.cesguiro.daw1.bookstore.util.exception;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class CustomException extends RuntimeException {

    protected Logger logger = LogManager.getLogger(this.getClass());
    protected String message;
    protected int statusCode;

    public CustomException(String message, int statusCode) {
        super(message);
        this.statusCode = statusCode;
        this.message = message;
        logError();
    }

    public CustomException(String message, int statusCode, Throwable cause) {
        super(message, cause);
        this.statusCode = statusCode;
        this.message = message;
        logError();
    }

    private void logError() {
        logger.error("{}", getMessage());
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getMessage() {
        return message;
    }
}
