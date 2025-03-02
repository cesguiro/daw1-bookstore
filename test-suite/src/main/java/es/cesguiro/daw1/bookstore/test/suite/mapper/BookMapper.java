package es.cesguiro.daw1.bookstore.test.suite.mapper;

import es.cesguiro.daw1.bookstore.domain.model.Author;
import es.cesguiro.daw1.bookstore.domain.model.Book;
import es.cesguiro.daw1.bookstore.domain.model.Publisher;
import es.cesguiro.daw1.bookstore.domain.model.vo.LocaleString;
import es.cesguiro.daw1.bookstore.persistence.dao.jdbc.model.AuthorRecord;
import es.cesguiro.daw1.bookstore.persistence.dao.jdbc.model.BookRecord;
import es.cesguiro.daw1.bookstore.persistence.dao.jdbc.model.PublisherRecord;
import es.cesguiro.daw1.bookstore.test.suite.AuthorsDataLoader;
import es.cesguiro.daw1.bookstore.test.suite.BooksAuthorsDataLoader;
import es.cesguiro.daw1.bookstore.test.suite.PublishersDataLoader;
import org.apache.commons.csv.CSVRecord;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class BookMapper extends BaseMapper{

    private static final PublishersDataLoader publishersDataLoader = new PublishersDataLoader();
    private static final AuthorsDataLoader authorsDataLoader = new AuthorsDataLoader();

    public static BookRecord toBookRecord(CSVRecord csvRecord) {
        if (csvRecord == null) {
            return null;
        }
        long id = Long.parseLong(csvRecord.get("id"));
        PublisherRecord publisherRecord = PublisherMapper.toPublisherRecord(getPublisherCsvRecord(Long.parseLong(csvRecord.get("publisher_id"))));
        List<AuthorRecord> authorRecords = getAuthorCsvRecords(id).stream().map(AuthorMapper::toAuthorRecord).toList();
        return new BookRecord(
                id,
                csvRecord.get("isbn"),
                csvRecord.get("title_es"),
                csvRecord.get("title_en"),
                csvRecord.get("synopsis_es"),
                csvRecord.get("synopsis_en"),
                new BigDecimal(csvRecord.get("base_price")),
                Double.parseDouble(csvRecord.get("discount_percentage")),
                csvRecord.get("cover"),
                parseDate(csvRecord.get("publication_date")),
                publisherRecord,
                authorRecords
        );
    }

    public static Book toBook(CSVRecord csvRecord) {
        if (csvRecord == null) {
            return null;
        }
        Publisher publisher = PublisherMapper.toPublisher(getPublisherCsvRecord(Long.parseLong(csvRecord.get("publisher_id"))));
        List<Author> authors = getAuthorCsvRecords(Long.parseLong(csvRecord.get("id"))).stream().map(AuthorMapper::toAuthor).toList();
        Book book = new Book(
                csvRecord.get("isbn"),
                new LocaleString(csvRecord.get("title_es"), csvRecord.get("title_en")),
                new LocaleString(csvRecord.get("synopsis_es"), csvRecord.get("synopsis_en")),
                new BigDecimal(csvRecord.get("base_price")),
                Double.parseDouble(csvRecord.get("discount_percentage")),
                csvRecord.get("cover"),
                parseDate(csvRecord.get("publication_date"))
        );
        book.setPublisher(publisher);
        book.setAuthors(authors);
        return book;
    }


    private static CSVRecord getPublisherCsvRecord(Long id) {
        return publishersDataLoader.findCsvRecordById(id).orElse(null);
    }

    private static List<CSVRecord> getAuthorCsvRecords(Long id) {
        BooksAuthorsDataLoader booksAuthorsDataLoader = new BooksAuthorsDataLoader();
        Long[] authorIds = booksAuthorsDataLoader.getAllAuthorIdsByBookId(id);
        return authorsDataLoader.findAllCsvRecordsByIds(authorIds);
    }

}
