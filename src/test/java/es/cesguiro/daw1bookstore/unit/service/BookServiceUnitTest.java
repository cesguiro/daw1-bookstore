package es.cesguiro.daw1bookstore.unit.service;

import es.cesguiro.daw1bookstore.common.container.BookIoc;
import es.cesguiro.daw1bookstore.common.exception.ResourceNotFoundException;
import es.cesguiro.daw1bookstore.domain.model.Book;
import es.cesguiro.daw1bookstore.domain.service.BookService;
import es.cesguiro.daw1bookstore.mock.repository.BookRepositoryMock;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("BookService Unit Tests")
public class BookServiceUnitTest {

    private static BookService bookService;

    @BeforeAll
    public static void setUp() {
        BookIoc.setBookRepository(new BookRepositoryMock());
        bookService = BookIoc.getBookService();
    }

    @DisplayName("test find all books")
    @Test
    public void testFindAll() {
        List<Book> actualBookList = bookService.findAll();
        List<Book> expectedBookList = List.of(
                new Book(1, "9788433920423", "La conjura de los necios", null, null, null),
                new Book(2, "9788426418197", "El nombre de la rosa", null, null, null),
                new Book(3, "9786074213485", "La insoportable levedad del ser", null, null, null),
                new Book(4, "9788466338141", "La isla del día de antes", null, null, null),
                new Book(5, "9788448022440", "Buenos presagios", null, null, null)
        );
        assertEquals(expectedBookList, actualBookList, "Libro incorrecto");
    }

    @DisplayName("test find book by id")
    @Test
    public void testFindById() {
        Book actualBook = bookService.findById(1);
        Book expectedBook = new Book(1, "9788433920423", "La conjura de los necios", null, null, null);
        assertEquals(expectedBook, actualBook, "Libro incorrecto");
    }

    @DisplayName("test find book by non-existent id")
    @Test
    public void testFindByIdNonExistent() {
        Exception exception = assertThrows(ResourceNotFoundException.class, () -> {
            Book actualBook = bookService.findById(6);
        });
        assertEquals("Resource not found. Book with id 6 not found.", exception.getMessage(), "Mensaje de error incorrecto");
    }
}
