package es.cesguiro.daw1.bookstore.domain.exception;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ValidationException extends RuntimeException {

    private final Logger logger = LogManager.getLogger(this.getClass());

    public ValidationException(String message) {
        super(message);
        logger.error("ValidationException: {}", message);
    }
}
