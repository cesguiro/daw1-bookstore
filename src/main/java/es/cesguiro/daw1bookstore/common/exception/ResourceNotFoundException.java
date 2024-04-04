package es.cesguiro.daw1bookstore.common.exception;

public class ResourceNotFoundException extends RuntimeException{

        private static final String message = "Resource not found. ";
        public ResourceNotFoundException(String message) {
            super(ResourceNotFoundException.message + message);
        }
}
