package es.cesguiro.daw1.bookstore.domain.exception;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;

public class ValidationException extends RuntimeException {

    private final Logger logger = LogManager.getLogger(this.getClass());
    private Map<String, String> errors;

    public ValidationException(String message) {
        super(message);
        logger.error("ValidationException: {}", message);
    }

    public ValidationException(String message, Map<String, String> errors) {
        super(message);
        this.errors = errors;
        logger.error("ValidationException: {}", errors);
    }

    public Map<String, String> getErrors() {
        return errors;
    }
}
