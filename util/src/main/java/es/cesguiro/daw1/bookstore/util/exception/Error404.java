package es.cesguiro.daw1.bookstore.util.exception;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Error404 extends CustomException {

    private static final String DESCRIPTION = "Not found error";

    public Error404(String message) {
        super(DESCRIPTION + ". " + message, 404);
    }
}
