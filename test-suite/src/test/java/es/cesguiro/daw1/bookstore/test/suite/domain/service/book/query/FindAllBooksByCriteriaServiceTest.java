package es.cesguiro.daw1.bookstore.test.suite.domain.service.book.query;

import es.cesguiro.daw1.bookstore.domain.model.Book;
import es.cesguiro.daw1.bookstore.domain.repository.AuthorRepository;
import es.cesguiro.daw1.bookstore.domain.repository.BookRepository;
import es.cesguiro.daw1.bookstore.domain.service.book.FindAllBooksByCriteriaService;
import es.cesguiro.daw1.bookstore.test.suite.BooksDataLoader;
import es.cesguiro.daw1.bookstore.util.pagination.Page;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FindAllBooksByCriteriaServiceTest {

    @Mock
    private BookRepository bookRepository;
    @Mock
    private AuthorRepository authorRepository;

    @InjectMocks
    private FindAllBooksByCriteriaService findAllBooksByCriteriaService;

    private static List<Book> books;

    @BeforeAll
    static void setUp() {
        BooksDataLoader booksDataLoader = new BooksDataLoader();
        books = booksDataLoader.loadBooksFromCSV();
    }

    static Stream<Arguments> provideFindAllArguments() {
        return Stream.of(
                Arguments.of(1, 10, 2L, books.subList(0,2), new Page<>(books.subList(0, 2), 1, 10, 2)),
                Arguments.of(1, 3, 3L, books.subList(0,3), new Page<>(books.subList(0, 3), 1, 3, 3)),
                Arguments.of(1, 3, 9L, books.subList(0,3), new Page<>(books.subList(0, 3), 1, 3, 9)),
                Arguments.of(2, 3, 5L, books.subList(3,5), new Page<>(books.subList(3, 5), 2, 3, 5))
        );
    }

    @ParameterizedTest
    @MethodSource("provideFindAllArguments")
    @DisplayName("Test execute method returns Page<BookCollectionQuery> with locale")
    void findAllReturnsBookCollectionQueryListLocale(int page, int size, long count, List<Book> bookEntities, Page<Book> expected) {
        when(bookRepository.findAll(page, size)).thenReturn(new Page<>(bookEntities, page, size, count));

        for (int i = 0; i < bookEntities.size(); i++) {
            when(authorRepository.findAllByBookIsbn(bookEntities.get(i).getIsbn())).thenReturn(bookEntities.get(i).getAuthors());
        }

        Page<Book> result = findAllBooksByCriteriaService.findAll(page, size);

        assertAll(
                () -> assertEquals(expected.data().size(), result.data().size(), "Result list size should match"),
                () -> assertEquals(expected.data().getFirst().getIsbn(), result.data().getFirst().getIsbn(), "First book ISBN should match"),
                () -> assertEquals(expected.data().getLast().getIsbn(), result.data().getLast().getIsbn(), "Last book ISBN should match"),
                () -> assertEquals(expected.data().getFirst().getTitle("es"), result.data().getFirst().getTitle("es"), "First book title should match"),
                () -> assertEquals(expected.data().getLast().getTitle("en"), result.data().getLast().getTitle("en"), "Last book title should match"),
                () -> assertEquals(expected.data().getFirst().getAuthors().size(), result.data().getFirst().getAuthors().size(), "First book authors size should match"),
                () -> assertEquals(expected.data().getLast().getAuthors().size(), result.data().getLast().getAuthors().size(), "Last book authors size should match"),
                () -> assertEquals(expected.data().getFirst().getAuthors().getFirst().getName(), result.data().getFirst().getAuthors().getFirst().getName(), "First book author name should match"),
                () -> assertEquals(expected.data().getLast().getAuthors().getLast().getName(), result.data().getLast().getAuthors().getLast().getName(), "Last book author name should match")
        );


        Mockito.verify(bookRepository).findAll(page, size);
        Mockito.verify(authorRepository).findAllByBookIsbn(bookEntities.getFirst().getIsbn());
        Mockito.verify(authorRepository).findAllByBookIsbn(bookEntities.getLast().getIsbn());
    }

}