package es.cesguiro.daw1bookstore.common.exception;

public class ResourceNotFoundException extends RuntimeException{

        public ResourceNotFoundException(String message) {
            super("Resource not found: " + message);
        }
}
