package es.cesguiro.daw1.bookstore.domain.exception;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BusinessException extends RuntimeException {

    private Logger logger = LogManager.getLogger(this.getClass());

    public BusinessException(String message) {
        super(message);
        logger.error("BusinessException: {}", message);
    }
}
