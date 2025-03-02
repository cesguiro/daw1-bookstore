package es.cesguiro.daw1.bookstore.persistence.dao.jdbc.mapper;

import es.cesguiro.daw1.bookstore.persistence.dao.jdbc.model.PublisherRecord;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PublisherMapper {

    public static PublisherRecord toPublisherRecord(ResultSet resultSet) throws SQLException {
        if (resultSet == null) {
            return null;
        }
        return new PublisherRecord(
                resultSet.getLong("id"),
                resultSet.getString("name"),
                resultSet.getString("slug")
        );
    }

    public static List<PublisherRecord> toPublisherRecords(ResultSet resultSet) throws SQLException {
        List<PublisherRecord> publisherRecords = new ArrayList<>();
        while (resultSet.next()) {
            publisherRecords.add(toPublisherRecord(resultSet));
        }
        return publisherRecords;
    }
}
