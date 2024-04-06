package es.cesguiro.daw1bookstore.common.exception;

public class QueryBuilderSQLException extends RuntimeException{

    public QueryBuilderSQLException(String message) {
        super("SQL exception: " + message);
    }
}
