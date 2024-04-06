package es.cesguiro.daw1bookstore.persistence.dao.impl.jdbc.mapper;

import es.cesguiro.daw1bookstore.domain.model.Publisher;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PublisherMapper {

    public static Publisher toPublisher(ResultSet resultSet) throws SQLException {
        if(resultSet == null) {
            return null;
        }
        return new Publisher(
                resultSet.getInt("id"),
                resultSet.getString("name")
        );
    }
}
