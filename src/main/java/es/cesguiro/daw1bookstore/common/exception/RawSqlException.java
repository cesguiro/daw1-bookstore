package es.cesguiro.daw1bookstore.common.exception;

public class RawSqlException extends RuntimeException{

        public RawSqlException(String message) {
            super("Raw SQL exception: " + message);
        }
}
