package es.cesguiro.daw1bookstore.persistence.dao.impl.jdbc.mapper;

import es.cesguiro.daw1bookstore.domain.model.Author;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AuthorMapper {

    public static Author toAuthor(ResultSet resultSet) throws SQLException {
        if(resultSet == null) {
            return null;
        }
        return new Author(
                resultSet.getInt("id"),
                resultSet.getString("name")
        );
    }

    public static List<Author> toAuthorList(ResultSet resultSet) throws SQLException {
        List<Author> authorList = new ArrayList<>();
        while (resultSet.next()) {
            authorList.add(toAuthor(resultSet));
        }
        return authorList;
    }
}
