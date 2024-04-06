package es.cesguiro.daw1bookstore.persistence.dao.impl.jdbc.mapper;

import es.cesguiro.daw1bookstore.common.exception.QueryBuilderSQLException;
import es.cesguiro.daw1bookstore.domain.model.Book;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookMapper {

    public static Book toBook(ResultSet resultSet) throws SQLException {
        if(resultSet == null) {
            return null;
        }
        return new Book(
                resultSet.getInt("id"),
                resultSet.getString("isbn"),
                resultSet.getString("title"),
                resultSet.getString("synopsis"),
                new BigDecimal(resultSet.getDouble("price")),
                resultSet.getString("cover")
        );
    }

    public static List<Book> toBookList(ResultSet resultSet) throws SQLException {
        List<Book> bookList = new ArrayList<>();
        while (resultSet.next()) {
            bookList.add(toBook(resultSet));
        }
        return bookList;
    }
}
