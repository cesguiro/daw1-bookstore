package es.cesguiro.daw1.bookstore.test.suite;

import es.cesguiro.daw1.bookstore.domain.model.Author;
import es.cesguiro.daw1.bookstore.persistence.dao.jdbc.model.AuthorRecord;
import es.cesguiro.daw1.bookstore.test.suite.mapper.AuthorMapper;
import org.apache.commons.csv.CSVRecord;

import java.util.List;

public class AuthorsDataLoader extends ResourceDataLoader {

    private final List<CSVRecord> authorRawRecords;

    public AuthorsDataLoader() {
        super("authors.csv");
        this.authorRawRecords = loadDataFromCsv();
    }


    public List<AuthorRecord> loadAuthorRecordsFromCSV() {
        return authorRawRecords
                .stream()
                .map(AuthorMapper::toAuthorRecord)
                .toList();
    }

    public List<Author> loadAuthorsFromCSV() {
        return authorRawRecords
                .stream()
                .map(AuthorMapper::toAuthor)
                .toList();
    }

}
