package es.cesguiro.daw1.bookstore.test.suite.domain.service.book.query;

import es.cesguiro.daw1.bookstore.domain.exception.BusinessException;
import es.cesguiro.daw1.bookstore.domain.model.Book;
import es.cesguiro.daw1.bookstore.domain.repository.AuthorRepository;
import es.cesguiro.daw1.bookstore.domain.repository.BookRepository;
import es.cesguiro.daw1.bookstore.domain.repository.PublisherRepository;
import es.cesguiro.daw1.bookstore.domain.service.book.FindBookByCriteriaService;
import es.cesguiro.daw1.bookstore.test.suite.BooksDataLoader;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FindBookByCriteriaServiceTest {

    @Mock
    private BookRepository bookRepository;
    @Mock
    private AuthorRepository authorRepository;
    @Mock
    private PublisherRepository publisherRepository;

    private static List<Book> books;

    @BeforeAll
    static void setUp() {
        BooksDataLoader booksDataLoader = new BooksDataLoader();
        books = booksDataLoader.loadBooksFromCSV();
    }

    @InjectMocks
    private FindBookByCriteriaService findByCriterialService;

    static Stream<Arguments> provideFindByIsbnArguments() {
        return Stream.of(
                Arguments.of(books.getFirst(), Optional.of(books.getFirst())),
                Arguments.of(books.get(1), Optional.of(books.get(1))),
                Arguments.of(books.get(10), Optional.of(books.get(10)))
        );
    }

    @ParameterizedTest
    @MethodSource("provideFindByIsbnArguments")
    @DisplayName("Test execute method return BookQuery")
    void testFindByIsbnMethodReturnBookQuery(Book book, Optional<Book> expected) {
        when(bookRepository.findByIsbn(book.getIsbn())).thenReturn(Optional.of(book));
        when(authorRepository.findAllByBookIsbn(book.getIsbn())).thenReturn(book.getAuthors());
        when(publisherRepository.findByBookIsbn(book.getIsbn())).thenReturn(Optional.of(book.getPublisher()));

        Book result = findByCriterialService.findByIsbn(book.getIsbn());

        assertAll("bookQuery",
                () -> assertNotNull(result, "BookQuery should not be null"),
                () -> assertEquals(expected.get().getIsbn(), result.getIsbn(), "Isbn should match"),
                () -> assertEquals(expected.get().getTitle("es"), result.getTitle("es"), "TitleEs should match"),
                () -> assertEquals(expected.get().getTitle("en"), result.getTitle("en"), "TitleEn should match"),
                () -> assertEquals(expected.get().getAuthors().size(), result.getAuthors().size(), "Authors size should match"),
                () -> assertEquals(expected.get().getPublisher().getName(), result.getPublisher().getName(), "Publisher name should match"),
                () -> assertEquals(expected.get().getFinalPrice(), result.getFinalPrice(), "Final price should match")
        );
    }

    @Test
    @DisplayName("Test findByIsbn throws BusinessException when book is not found")
    void testFindByIsbnThrowsExceptionWhenBookNotFound() {
        String nonExistentIsbn = "456";
        when(bookRepository.findByIsbn(nonExistentIsbn)).thenReturn(Optional.empty());

        assertThrows(BusinessException.class,
                () -> findByCriterialService.findByIsbn(nonExistentIsbn),
                "Should throw Error404 when book is not found");
    }


}