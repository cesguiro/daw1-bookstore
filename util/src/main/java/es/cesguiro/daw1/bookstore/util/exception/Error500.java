package es.cesguiro.daw1.bookstore.util.exception;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class Error500 extends CustomException {

    public Error500(String message) {
        super(message, 500);
    }

    public Error500(String message, Throwable cause) {
        super(message, 500, cause);
    }

}
