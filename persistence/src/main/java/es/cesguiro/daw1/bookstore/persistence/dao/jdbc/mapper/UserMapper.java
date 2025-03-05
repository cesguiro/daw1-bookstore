package es.cesguiro.daw1.bookstore.persistence.dao.jdbc.mapper;

import es.cesguiro.daw1.bookstore.persistence.dao.jdbc.model.UserRecord;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper {

    public static UserRecord toUserRecord(ResultSet resultSet) throws SQLException {
        if (resultSet == null) {
            return null;
        }
        return new UserRecord(
                resultSet.getLong("id"),
                resultSet.getString("email"),
                resultSet.getString("password"),
                resultSet.getString("name"),
                resultSet.getString("address"),
                resultSet.getString("language").toLowerCase(),
                resultSet.getBoolean("admin")
        );
    }
}
