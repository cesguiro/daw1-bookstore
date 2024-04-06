package es.cesguiro.daw1bookstore.persistence.dao.impl.jdbc.mapper;

import es.cesguiro.daw1bookstore.domain.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper {

    public static User toUser(ResultSet resultSet) throws SQLException {
        return new User(
                resultSet.getInt("id"),
                resultSet.getString("username"),
                resultSet.getString("password"),
                resultSet.getString("email"),
                resultSet.getString("name"),
                resultSet.getString("surname"),
                resultSet.getString("address"),
                resultSet.getBoolean("admin")
        );
    }
}
