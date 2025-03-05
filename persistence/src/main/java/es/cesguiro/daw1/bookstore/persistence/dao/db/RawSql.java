package es.cesguiro.daw1.bookstore.persistence.dao.db;

import es.cesguiro.daw1.bookstore.util.context.RequestContextHolder;

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
            throw new RuntimeException("Error executing query: " + query + " with params: " + params, e);
        }
    }

    public static Long insert(String query, List<Object> params) {
        try {
            PreparedStatement preparedStatement = setParameter(query, params);
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                return resultSet.getLong(1);
            }
            throw new RuntimeException("Error inserting record");
        } catch (Exception e) {
            throw new RuntimeException("Error executing insert query: " + query + " with params: " + params, e);
        }
    }

    public static int update(String query, List<Object> params) {
        try {
            return setParameter(query, params).executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException("Error executing update query: " + query + " with params: " + params, e);
        }
    }

    public static int delete(String query, List<Object> params) {
        try {
            return setParameter(query, params).executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException("Error executing delete query: " + query + " with params: " + params, e);
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
            throw new RuntimeException("Error setting parameters", e);
        }
    }


    public static void commit() {
        try {
            RequestContextHolder.getRequestContext().getConnection().commit();
        } catch (Exception e) {
            throw new RuntimeException("Error committing transaction", e);
        }
    }

    public static void rollback() {
        try {
            RequestContextHolder.getRequestContext().getConnection().rollback();
        } catch (Exception e) {
            throw new RuntimeException("Error rolling back transaction", e);
        }
    }
}
