package es.cesguiro.daw1.bookstore.test.suite.mapper;

import es.cesguiro.daw1.bookstore.domain.model.Author;
import es.cesguiro.daw1.bookstore.domain.model.vo.LocaleString;
import es.cesguiro.daw1.bookstore.persistence.dao.jdbc.model.AuthorRecord;
import org.apache.commons.csv.CSVRecord;

public class AuthorMapper extends BaseMapper {

    public static AuthorRecord toAuthorRecord(CSVRecord csvRecord) {
        if (csvRecord == null) {
            return null;
        }
        return new AuthorRecord(
                Long.parseLong(csvRecord.get("id")),
                csvRecord.get("name"),
                csvRecord.get("nationality"),
                csvRecord.get("biography_es"),
                csvRecord.get("biography_en"),
                Integer.parseInt(csvRecord.get("birth_year")),
                parseInt(csvRecord.get("death_year")) != null ? Integer.parseInt(csvRecord.get("death_year")) : 0,
                csvRecord.get("slug")
        );
    }

    public static Author toAuthor(CSVRecord csvRecord) {
        if (csvRecord == null) {
            return null;
        }
        return new Author(
                csvRecord.get("name"),
                csvRecord.get("nationality"),
                new LocaleString(csvRecord.get("biography_es"), csvRecord.get("biography_en")),
                Integer.parseInt(csvRecord.get("birth_year")),
                parseInt(csvRecord.get("death_year")) != null ? Integer.parseInt(csvRecord.get("death_year")) : 0,
                csvRecord.get("slug")
        );
    }


}
