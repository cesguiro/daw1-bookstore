package es.cesguiro.daw1.bookstore.persistence.dao.jdbc.mapper;

import es.cesguiro.daw1.bookstore.persistence.dao.jdbc.model.BookRecord;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookMapper {

    public static BookRecord toBookRecord(ResultSet resultSet) throws SQLException {
        if (resultSet == null) {
            return null;
        }
        return new BookRecord(
                resultSet.getLong("id"),
                resultSet.getString("isbn"),
                resultSet.getString("title_es"),
                resultSet.getString("title_en"),
                resultSet.getString("synopsis_es"),
                resultSet.getString("synopsis_En"),
                resultSet.getBigDecimal("base_price"),
                resultSet.getDouble("discount_percentage"),
                resultSet.getString("cover"),
                resultSet.getDate("publication_date").toLocalDate(),
                null,
                null
        );
    }

    public static List<BookRecord> toBookRecords(ResultSet resultSet) throws SQLException {
        List<BookRecord> bookRecords = new ArrayList<>();
        while (resultSet.next()) {
            bookRecords.add(toBookRecord(resultSet));
        }
        return bookRecords;
    }
}
