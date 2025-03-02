package es.cesguiro.daw1.bookstore.test.suite;

import es.cesguiro.daw1.bookstore.domain.model.Book;
import es.cesguiro.daw1.bookstore.persistence.dao.jdbc.model.BookRecord;
import es.cesguiro.daw1.bookstore.test.suite.mapper.BookMapper;
import org.apache.commons.csv.CSVRecord;

import java.util.List;

public class BooksDataLoader extends ResourceDataLoader {

    private final List<CSVRecord> bookRawRecords;

    public BooksDataLoader() {
        super("books.csv");
        bookRawRecords = loadDataFromCsv();
    }


    public List<BookRecord> loadBookRecordsFromCSV() {
        return bookRawRecords
                .stream()
                .map(BookMapper::toBookRecord)
                .toList();
    }

    public List<Book> loadBooksFromCSV() {
        return bookRawRecords
                .stream()
                .map(BookMapper::toBook)
                .toList();
    }

}
