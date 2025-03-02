package es.cesguiro.daw1.bookstore.persistence.dao.jdbc.mapper;


import es.cesguiro.daw1.bookstore.persistence.dao.jdbc.model.AuthorRecord;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AuthorMapper {

    public static AuthorRecord toAuthorRecord(ResultSet resultSet) throws SQLException {
        if (resultSet == null) {
            return null;
        }
        return new AuthorRecord(
                resultSet.getLong("id"),
                resultSet.getString("name"),
                resultSet.getString("nationality"),
                resultSet.getString("biography_Es"),
                resultSet.getString("biography_En"),
                resultSet.getInt("birth_year"),
                resultSet.getInt("death_year"),
                resultSet.getString("slug")
        );
    }

    public static List<AuthorRecord> toAuthorRecords(ResultSet resultSet) throws SQLException {
        List<AuthorRecord> authorRecords = new ArrayList<>();
        while (resultSet.next()) {
            authorRecords.add(toAuthorRecord(resultSet));
        }
        return authorRecords;
    }
}
