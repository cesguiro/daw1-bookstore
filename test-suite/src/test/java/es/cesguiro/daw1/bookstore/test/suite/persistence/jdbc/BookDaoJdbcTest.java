package es.cesguiro.daw1.bookstore.test.suite.persistence.jdbc;

import es.cesguiro.daw1.bookstore.persistence.dao.BookDao;
import es.cesguiro.daw1.bookstore.persistence.dao.jdbc.BookDaoJdbc;
import es.cesguiro.daw1.bookstore.persistence.dao.jdbc.model.BookRecord;
import es.cesguiro.daw1.bookstore.test.suite.BooksDataLoader;
import es.cesguiro.daw1.bookstore.test.suite.persistence.FlywayJdbcTest;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class BookDaoJdbcTest extends FlywayJdbcTest {

    private final BookDao bookDao = new BookDaoJdbc();

    private static List<BookRecord> bookRecords;

    @BeforeAll
    static void beforeAll() throws URISyntaxException {
        BooksDataLoader loader = new BooksDataLoader();
        bookRecords = loader.loadBookRecordsFromCSV();
    }


    static Stream<Arguments> provideFindAllArguments() {
        return Stream.of(
                Arguments.of(1, 10, bookRecords.subList(0, 10)),
                Arguments.of(2, 10, bookRecords.subList(10, 20)),
                Arguments.of(3, 10, bookRecords.subList(20, 24)),
                Arguments.of(4, 10, List.of())
        );
    }

    @ParameterizedTest(name = "Find all books with page {0} and size {1}")
    @DisplayName("Find all books with page and size")
    @MethodSource("provideFindAllArguments")
    void findAllBooksWithPageAndSize(int page, int size, List<BookRecord> expectedBooks) {
        List<BookRecord> result = bookDao.findAll(page, size);
        assertEquals(expectedBooks.size(), result.size(), "Size should match");

        for (int i = 0; i < expectedBooks.size(); i++) {
            BookRecord expectedBook = expectedBooks.get(i);
            BookRecord actualBook = result.get(i);
            assertAll(
                    () -> assertEquals(expectedBook.id(), actualBook.id(), "ID should match"),
                    () -> assertEquals(expectedBook.isbn(), actualBook.isbn(), "ISBN should match"),
                    () -> assertEquals(expectedBook.titleEn(), actualBook.titleEn(), "Title should match"),
                    () -> assertEquals(expectedBook.titleEs(), actualBook.titleEs(), "Title should match"),
                    () -> assertEquals(expectedBook.synopsisEn(), actualBook.synopsisEn(), "Synopsis should match"),
                    () -> assertEquals(expectedBook.synopsisEs(), actualBook.synopsisEs(), "Synopsis should match"),
                    () -> assertEquals(expectedBook.basePrice(), actualBook.basePrice(), "Base price should match"),
                    () -> assertEquals(expectedBook.discountPercentage(), actualBook.discountPercentage(), "Discount percentage should match"),
                    () -> assertEquals(expectedBook.cover(), actualBook.cover(), "Cover should match"),
                    () -> assertEquals(expectedBook.publicationDate(), actualBook.publicationDate(), "Publication date should match")
            );
        }
    }

    static Stream<Arguments> provideFindByIsbnArguments() {
        return Stream.of(
                Arguments.of("9780060256654", bookRecords.get(3)),
                Arguments.of("9780060557912", bookRecords.get(21)),
                Arguments.of("123", null)
        );
    }

    @ParameterizedTest(name = "ISBN {0} should return a book: {1}")
    @MethodSource("provideFindByIsbnArguments")
    @DisplayName("FindByIsbn should return the correct book or an empty optional")
    void findByIsbnShouldReturnCorrectResult(String isbn, BookRecord expectedBook) {
        Optional<BookRecord> result = bookDao.findByIsbn(isbn);

        if (expectedBook != null) {
            assertAll(
                    () -> assertTrue(result.isPresent(), "Book should be present"),
                    () -> assertEquals(expectedBook.id(), result.get().id(), "ID should match"),
                    () -> assertEquals(expectedBook.isbn(), result.get().isbn(), "ISBN should match"),
                    () -> assertEquals(expectedBook.titleEn(), result.get().titleEn(), "Title should match"),
                    () -> assertEquals(expectedBook.titleEs(), result.get().titleEs(), "Title should match"),
                    () -> assertEquals(expectedBook.synopsisEn(), result.get().synopsisEn(), "Synopsis should match"),
                    () -> assertEquals(expectedBook.synopsisEs(), result.get().synopsisEs(), "Synopsis should match"),
                    () -> assertEquals(expectedBook.basePrice(), result.get().basePrice(), "Base price should match"),
                    () -> assertEquals(expectedBook.discountPercentage(), result.get().discountPercentage(), "Discount percentage should match"),
                    () -> assertEquals(expectedBook.cover(), result.get().cover(), "Cover should match"),
                    () -> assertEquals(expectedBook.publicationDate(), result.get().publicationDate(), "Publication date should match")
            );
        } else {
            assertTrue(result.isEmpty(), "Optional should be empty");
        }

    }

    @Test
    @DisplayName("Count all books should return the total number of books")
    void countAllBooksShouldReturnTheTotalNumberOfBooks() {
        long result = bookDao.count();
        assertEquals(bookRecords.size(), result, "Count should match");
    }


    /********** RawSql **********/

    @ParameterizedTest(name = "Find all books with page {0} and size {1}")
    @MethodSource("provideFindAllArguments")
    @DisplayName("Find all books using RawSql should return a list of books")
    void findAllBooksUsingRawSqlShouldReturnAListOfBooks(int page, int size, List<BookRecord> expectedBooks) {
        List<BookRecord> result = bookDao.findAllRawSql(page, size);
        assertEquals(expectedBooks.size(), result.size(), "Size should match");

        for (int i = 0; i < expectedBooks.size(); i++) {
            BookRecord expectedBook = expectedBooks.get(i);
            BookRecord actualBook = result.get(i);
            assertAll(
                    () -> assertEquals(expectedBook.id(), actualBook.id(), "ID should match"),
                    () -> assertEquals(expectedBook.isbn(), actualBook.isbn(), "ISBN should match"),
                    () -> assertEquals(expectedBook.titleEn(), actualBook.titleEn(), "Title should match"),
                    () -> assertEquals(expectedBook.titleEs(), actualBook.titleEs(), "Title should match"),
                    () -> assertEquals(expectedBook.synopsisEn(), actualBook.synopsisEn(), "Synopsis should match"),
                    () -> assertEquals(expectedBook.synopsisEs(), actualBook.synopsisEs(), "Synopsis should match"),
                    () -> assertEquals(expectedBook.basePrice(), actualBook.basePrice(), "Base price should match"),
                    () -> assertEquals(expectedBook.discountPercentage(), actualBook.discountPercentage(), "Discount percentage should match"),
                    () -> assertEquals(expectedBook.cover(), actualBook.cover(), "Cover should match"),
                    () -> assertEquals(expectedBook.publicationDate(), actualBook.publicationDate(), "Publication date should match")
            );
        }
    }

    @ParameterizedTest(name = "ISBN {0} should return a book: {1}")
    @MethodSource("provideFindByIsbnArguments")    @DisplayName("FindByIsbn using RawSql should return the correct book or an empty optional")
    void findByIsbnUsingRawSqlShouldReturnCorrectResult(String isbn, BookRecord expectedBook) {
        Optional<BookRecord> result = bookDao.findByIsbnRawSql(isbn);

        if (expectedBook != null) {
            assertAll(
                    () -> assertTrue(result.isPresent(), "Book should be present"),
                    () -> assertEquals(expectedBook.id(), result.get().id(), "ID should match"),
                    () -> assertEquals(expectedBook.isbn(), result.get().isbn(), "ISBN should match"),
                    () -> assertEquals(expectedBook.titleEn(), result.get().titleEn(), "Title should match"),
                    () -> assertEquals(expectedBook.titleEs(), result.get().titleEs(), "Title should match"),
                    () -> assertEquals(expectedBook.synopsisEn(), result.get().synopsisEn(), "Synopsis should match"),
                    () -> assertEquals(expectedBook.synopsisEs(), result.get().synopsisEs(), "Synopsis should match"),
                    () -> assertEquals(expectedBook.basePrice(), result.get().basePrice(), "Base price should match"),
                    () -> assertEquals(expectedBook.discountPercentage(), result.get().discountPercentage(), "Discount percentage should match"),
                    () -> assertEquals(expectedBook.cover(), result.get().cover(), "Cover should match"),
                    () -> assertEquals(expectedBook.publicationDate(), result.get().publicationDate(), "Publication date should match")
            );
        } else {
            assertTrue(result.isEmpty(), "Optional should be empty");
        }
    }

    /********** QueryBuilder **********/

    @ParameterizedTest(name = "Find all books with page {0} and size {1}")
    @MethodSource("provideFindAllArguments")
    @DisplayName("Find all books using QueryBuilder should return a list of books")
    void findAllBooksUsingQueryBuilderShouldReturnAListOfBooks(int page, int size, List<BookRecord> expectedBooks) {
        List<BookRecord> result = bookDao.findAllQueryBuilder(page, size);
        assertEquals(expectedBooks.size(), result.size(), "Size should match");

        for (int i = 0; i < expectedBooks.size(); i++) {
            BookRecord expectedBook = expectedBooks.get(i);
            BookRecord actualBook = result.get(i);
            assertAll(
                    () -> assertEquals(expectedBook.id(), actualBook.id(), "ID should match"),
                    () -> assertEquals(expectedBook.isbn(), actualBook.isbn(), "ISBN should match"),
                    () -> assertEquals(expectedBook.titleEn(), actualBook.titleEn(), "Title should match"),
                    () -> assertEquals(expectedBook.titleEs(), actualBook.titleEs(), "Title should match"),
                    () -> assertEquals(expectedBook.synopsisEn(), actualBook.synopsisEn(), "Synopsis should match"),
                    () -> assertEquals(expectedBook.synopsisEs(), actualBook.synopsisEs(), "Synopsis should match"),
                    () -> assertEquals(expectedBook.basePrice(), actualBook.basePrice(), "Base price should match"),
                    () -> assertEquals(expectedBook.discountPercentage(), actualBook.discountPercentage(), "Discount percentage should match"),
                    () -> assertEquals(expectedBook.cover(), actualBook.cover(), "Cover should match"),
                    () -> assertEquals(expectedBook.publicationDate(), actualBook.publicationDate(), "Publication date should match")
            );
        }
    }

    @ParameterizedTest(name = "ISBN {0} should return a book: {1}")
    @MethodSource("provideFindByIsbnArguments")
    @DisplayName("FindByIsbn using QueryBuilder should return the correct book or an empty optional")
    void findByIsbnUsingQueryBuilderShouldReturnCorrectResult(String isbn, BookRecord expectedBook) {
        Optional<BookRecord> result = bookDao.findByIsbnQueryBuilder(isbn);

        if (expectedBook != null) {
            assertAll(
                    () -> assertTrue(result.isPresent(), "Book should be present"),
                    () -> assertEquals(expectedBook.id(), result.get().id(), "ID should match"),
                    () -> assertEquals(expectedBook.isbn(), result.get().isbn(), "ISBN should match"),
                    () -> assertEquals(expectedBook.titleEn(), result.get().titleEn(), "Title should match"),
                    () -> assertEquals(expectedBook.titleEs(), result.get().titleEs(), "Title should match"),
                    () -> assertEquals(expectedBook.synopsisEn(), result.get().synopsisEn(), "Synopsis should match"),
                    () -> assertEquals(expectedBook.synopsisEs(), result.get().synopsisEs(), "Synopsis should match"),
                    () -> assertEquals(expectedBook.basePrice(), result.get().basePrice(), "Base price should match"),
                    () -> assertEquals(expectedBook.discountPercentage(), result.get().discountPercentage(), "Discount percentage should match"),
                    () -> assertEquals(expectedBook.cover(), result.get().cover(), "Cover should match"),
                    () -> assertEquals(expectedBook.publicationDate(), result.get().publicationDate(), "Publication date should match")
            );
        } else {
            assertTrue(result.isEmpty(), "Optional should be empty");
        }
    }

}


