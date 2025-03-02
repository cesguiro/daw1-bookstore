package es.cesguiro.daw1.bookstore.util.exception;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Error400 extends CustomException {

    private static final String DESCRIPTION = "Bad request";

    public Error400(String message) {
        super(DESCRIPTION + ". " + message, 400);
    }
}
