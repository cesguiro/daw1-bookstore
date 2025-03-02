package es.cesguiro.daw1.bookstore.test.suite.persistence.integration;

import es.cesguiro.daw1.bookstore.domain.model.Book;
import es.cesguiro.daw1.bookstore.domain.repository.BookRepository;
import es.cesguiro.daw1.bookstore.persistence.dao.BookDao;
import es.cesguiro.daw1.bookstore.persistence.dao.jdbc.BookDaoJdbc;
import es.cesguiro.daw1.bookstore.persistence.repository.BookRepositoryJdbc;
import es.cesguiro.daw1.bookstore.test.suite.BooksDataLoader;
import es.cesguiro.daw1.bookstore.test.suite.persistence.FlywayJdbcTest;
import es.cesguiro.daw1.bookstore.util.exception.Error500;
import es.cesguiro.daw1.bookstore.util.pagination.Page;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class BookIntegrationTest extends FlywayJdbcTest {

    private final BookDao bookDao = new BookDaoJdbc();
    private final BookRepository bookRepository = new BookRepositoryJdbc(bookDao);

    private static List<Book> books;

    @BeforeAll
    static void beforeAll() {
        BooksDataLoader loader = new BooksDataLoader();
        books = loader.loadBooksFromCSV();
    }

    static Stream<Arguments> provideFindAllArguments() {
        return Stream.of(
                Arguments.of(1, 10, new Page<>(books.subList(0,10), 1, 10, 24)),
                Arguments.of(2, 10, new Page<>(books.subList(10, 20), 2, 10, 24)),
                Arguments.of(3, 10, new Page<>(books.subList(20, 24), 3, 10, 24))
        );
    }

    @ParameterizedTest
    @MethodSource("provideFindAllArguments")
    @DisplayName("Test find all books")
    public void testFindAllBooks(int page, int size, Page<Book> expected) {
        Page<Book> result = bookRepository.findAll(page, size);

        assertAll(
                () -> assertEquals(expected.pageNumber(), result.pageNumber(), "Page number should match"),
                () -> assertEquals(expected.pageSize(), result.pageSize(), "Page size should match"),
                () -> assertEquals(expected.totalPages(), result.totalPages(), "Total pages should match"),
                () -> assertEquals(expected.totalElements(), result.totalElements(), "Total elements should match"),
                () -> assertEquals(expected.data().size(), result.data().size(), "Data size should match"),
                () -> assertEquals(expected.totalPages(), result.totalPages(), "Total pages should match"),
                () -> assertEquals(expected.data().getFirst().getIsbn(), result.data().getFirst().getIsbn(), "First ISBN should match"),
                () -> assertEquals(expected.data().getLast().getIsbn(), result.data().getLast().getIsbn(), "Last ISBN should match")
        );
    }

    static Stream<Arguments> provideFindAllExceptionArguments() {
        return Stream.of(
                Arguments.of(0, 10),
                Arguments.of(2, 10, new Page<>(books.subList(10, 20), 2, 10, 24)),
                Arguments.of(3, 10, new Page<>(books.subList(20, 24), 1, 3, 24))
        );
    }

    @Test
    @DisplayName("Test find all books with invalid page")
    public void testFindAllBooksWithInvalidPage() {
        int page = 0;
        int size = 10;
        assertThrows(Error500.class, () -> bookRepository.findAll(page, size));
    }

    static Stream<Arguments> provideFindByIsbnArguments() {
        return Stream.of(
                Arguments.of("9780142424179", Optional.of(books.get(0))),
                Arguments.of("123", Optional.empty())
        );
    }


    @ParameterizedTest
    @MethodSource("provideFindByIsbnArguments")
    @DisplayName("Test find book by ISBN")
    public void testFindBookByIsbn(String isbn, Optional<Book> expected) {
        Optional<Book> result = bookRepository.findByIsbn(isbn);

        if (expected.isEmpty()) {
            assertTrue(result.isEmpty());
            return;
        }

        assertAll(
                () -> assertTrue(result.isPresent(), "Book should be present"),
                () -> assertEquals(expected.get().getIsbn(), result.get().getIsbn(), "ISBN should match"),
                () -> assertEquals(expected.get().getTitle("es"), result.get().getTitle("es"), "TitleEs should match"),
                () -> assertEquals(expected.get().getTitle("en"), result.get().getTitle("en"), "TitleEn should match"),
                () -> assertEquals(expected.get().getSynopsis("es"), result.get().getSynopsis("es"), "SynopsisEs should match"),
                () -> assertEquals(expected.get().getSynopsis("en"), result.get().getSynopsis("en"), "SynopsisEn should match"),
                () -> assertEquals(expected.get().getBasePrice(), result.get().getBasePrice(), "Base price should match"),
                () -> assertEquals(expected.get().getDiscountPercentage(), result.get().getDiscountPercentage(), "Discount percentage should match"),
                () -> assertEquals(expected.get().getCover(), result.get().getCover(), "Cover should match"),
                () -> assertEquals(expected.get().getPublicationDate(), result.get().getPublicationDate(), "Publication date should match")
        );
    }


}
