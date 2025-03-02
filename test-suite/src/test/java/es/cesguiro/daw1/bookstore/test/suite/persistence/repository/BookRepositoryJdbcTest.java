package es.cesguiro.daw1.bookstore.test.suite.persistence.repository;

import es.cesguiro.daw1.bookstore.domain.model.Book;
import es.cesguiro.daw1.bookstore.persistence.dao.BookDao;
import es.cesguiro.daw1.bookstore.persistence.dao.jdbc.model.BookRecord;
import es.cesguiro.daw1.bookstore.persistence.repository.BookRepositoryJdbc;
import es.cesguiro.daw1.bookstore.test.suite.BooksDataLoader;
import es.cesguiro.daw1.bookstore.util.pagination.Page;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BookRepositoryJdbcTest {

    private static List<Book> books;
    private static List<BookRecord> bookRecords;

    @Mock
    BookDao bookDao;
    @InjectMocks
    BookRepositoryJdbc bookRepositoryJdbc;

    @BeforeAll
    static void setUp() {
        BooksDataLoader loader = new BooksDataLoader();
        books = loader.loadBooksFromCSV();
        bookRecords = loader.loadBookRecordsFromCSV();
    }

    static Stream<Arguments> provideFindAllArguments() {
        return Stream.of(
                Arguments.of(1, 10, 2L, bookRecords.subList(0,2), new Page<>(books.subList(0, 2), 1, 10, 2)),
                Arguments.of(1, 10, 0L, Collections.emptyList(), new Page<>(Collections.emptyList(), 1, 10, 0)),
                Arguments.of(2, 10, 5L, Collections.emptyList(), new Page<>(Collections.emptyList(), 2, 10, 5)),
                Arguments.of(1, 3, 3L, bookRecords.subList(0,3), new Page<>(books.subList(0, 3), 1, 3, 3)),
                Arguments.of(1, 3, 9L, bookRecords.subList(0,3), new Page<>(books.subList(0, 3), 1, 3, 9)),
                Arguments.of(2, 3, 5L, bookRecords.subList(3,5), new Page<>(books.subList(3, 5), 2, 3, 5))
        );
    }

    @ParameterizedTest
    @MethodSource("provideFindAllArguments")
    @DisplayName("Test findAll method returns Page<BookEntity>")
    void testFindAll(int page, int size, Long count, List<BookRecord> bookRecords, Page<Book> expected) {
        when(bookDao.findAll(page, size)).thenReturn(bookRecords);
        when(bookDao.count()).thenReturn(count);

        Page<Book> result = bookRepositoryJdbc.findAll(page, size);

        assertAll(
                () -> assertNotNull(result, "Page should not be null"),
                () -> assertEquals(expected.data().size(), result.data().size(), "Data should contain " + expected.data().size()+ " elements"),
                () -> assertEquals(expected.pageNumber(), result.pageNumber(), "Page number should be " + expected.pageNumber()),
                () -> assertEquals(expected.pageSize(), result.pageSize(), "Page size should be " + expected.pageSize()),
                () -> assertEquals(expected.totalElements(), result.totalElements(), "Total elements should be " + expected.totalElements())
        );

        if (!expected.data().isEmpty()) {
            assertFalse(result.data().isEmpty(), "Result data should not be empty if expected data is not empty");
        }

        if (!expected.data().isEmpty()) {
            assertAll(
                    () -> assertFalse(result.data().isEmpty(), "Data should not be empty"),
                    () -> assertEquals(expected.data().getFirst().getIsbn(), result.data().getFirst().getIsbn(), "First isbn should match"),
                    () -> assertEquals(expected.data().getFirst().getTitle("es"), result.data().getFirst().getTitle("es"), "First title should match"),
                    () -> assertEquals(expected.data().getLast().getIsbn(), result.data().getLast().getIsbn(), "Last isbn should match"),
                    () -> assertEquals(expected.data().getLast().getTitle("en"), result.data().getLast().getTitle("en"), "Last title should match")
            );
        }
    }

    static Stream<Arguments> provideFindByIsbnArguments() {
        return Stream.of(
                Arguments.of("123", Optional.of(bookRecords.get(0)), Optional.of(books.get(0))),
                Arguments.of("456", Optional.empty(), Optional.empty()),
                Arguments.of(null, Optional.empty(), Optional.empty())
        );
    }

    @ParameterizedTest
    @MethodSource("provideFindByIsbnArguments")
    @DisplayName("Test findByIsbn method returns Optional<BookEntity>")
    void testFindByIsbn(String isbn, Optional<BookRecord> optionalBookRecord, Optional<Book> expected) {
        when(bookDao.findByIsbn(isbn)).thenReturn(optionalBookRecord);

        Optional<Book> result = bookRepositoryJdbc.findByIsbn(isbn);

        assertEquals(expected.isPresent(), result.isPresent(), "Presence of result does not match expectation");

        if (expected.isPresent()) {
            Book expectedEntity = expected.get();
            Book resultEntity = result.orElseThrow(() -> new AssertionError("Expected result to be present, but it was empty"));

            assertAll(
                    () -> assertEquals(expectedEntity.getIsbn(), resultEntity.getIsbn(), "ISBN mismatch"),
                    () -> assertEquals(expectedEntity.getTitle("es"), resultEntity.getTitle("es"), "TitleEs mismatch"),
                    () -> assertEquals(expectedEntity.getTitle("en"), resultEntity.getTitle("en"), "TitleEn mismatch"),
                    () -> assertEquals(expectedEntity.getSynopsis("es"), resultEntity.getSynopsis("es"), "SynopsisEs mismatch"),
                    () -> assertEquals(expectedEntity.getSynopsis("en"), resultEntity.getSynopsis("en"), "SynopsisEn mismatch"),
                    () -> assertEquals(expectedEntity.getBasePrice(), resultEntity.getBasePrice(), "Base price mismatch"),
                    () -> assertEquals(expectedEntity.getDiscountPercentage(), resultEntity.getDiscountPercentage(), "Discount percentage mismatch"),
                    () -> assertEquals(expectedEntity.getCover(), resultEntity.getCover(), "Cover mismatch")
            );
        }
    }


}