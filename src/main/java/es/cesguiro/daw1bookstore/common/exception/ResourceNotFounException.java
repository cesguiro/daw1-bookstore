package es.cesguiro.daw1bookstore.common.exception;

public class ResourceNotFounException extends RuntimeException{

        private static final String message = "Resource not found. ";
        public ResourceNotFounException(String message) {
            super(ResourceNotFounException.message + message);
        }
}
