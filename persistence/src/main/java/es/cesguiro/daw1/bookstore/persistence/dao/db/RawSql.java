package es.cesguiro.daw1.bookstore.persistence.dao.db;

import es.cesguiro.daw1.bookstore.util.context.RequestContextHolder;
import es.cesguiro.daw1.bookstore.util.exception.Error500;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

public class RawSql {

    public static ResultSet select(String query, List<Object> params) {
        try {
            return setParameter(query, params).executeQuery();
        } catch (Exception e) {
            throw new Error500("Error executing query: " + query + " with params: " + params, e);
        }
    }

    private static PreparedStatement setParameter(String query, List<Object> params) {
        try {
            Connection connection = RequestContextHolder.getRequestContext().getConnection();
            PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            if (params != null) {
                for (int i = 0; i < params.size(); i++) {
                    statement.setObject(i + 1, params.get(i));
                }
            }
            return statement;
        } catch (Exception e) {
            throw new Error500("Error setting parameters", e);
        }
    }


    public static void commit() {
        try {
            RequestContextHolder.getRequestContext().getConnection().commit();
        } catch (Exception e) {
            throw new Error500("Error committing transaction", e);
        }
    }

    public static void rollback() {
        try {
            RequestContextHolder.getRequestContext().getConnection().rollback();
        } catch (Exception e) {
            throw new Error500("Error rolling back transaction", e);
        }
    }
}
