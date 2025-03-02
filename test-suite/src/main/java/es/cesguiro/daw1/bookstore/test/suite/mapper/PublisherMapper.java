package es.cesguiro.daw1.bookstore.test.suite.mapper;

import es.cesguiro.daw1.bookstore.domain.model.Publisher;
import es.cesguiro.daw1.bookstore.persistence.dao.jdbc.model.PublisherRecord;
import org.apache.commons.csv.CSVRecord;

public class PublisherMapper extends BaseMapper {

    public static PublisherRecord toPublisherRecord(CSVRecord csvRecord) {
        if (csvRecord == null) {
            return null;
        }
        return new PublisherRecord(
                Long.parseLong(csvRecord.get("id")),
                csvRecord.get("name"),
                csvRecord.get("slug")
        );
    }

    public static Publisher toPublisher(CSVRecord csvRecord) {
        if (csvRecord == null) {
            return null;
        }
        return new Publisher(
                csvRecord.get("name"),
                csvRecord.get("slug")
        );
    }
}
