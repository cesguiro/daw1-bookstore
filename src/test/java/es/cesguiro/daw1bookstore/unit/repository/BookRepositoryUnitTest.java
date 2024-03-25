package es.cesguiro.daw1bookstore.unit.repository;

import es.cesguiro.daw1bookstore.common.container.BookIoc;
import es.cesguiro.daw1bookstore.domain.model.Book;
import es.cesguiro.daw1bookstore.mock.dao.BookDaoMock;
import es.cesguiro.daw1bookstore.persistence.repository.BookRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@DisplayName("BookRepository Unit Tests")
public class BookRepositoryUnitTest {

    private static BookRepository bookRepository;

    @BeforeAll
    public static void setUp() {
        BookIoc.setBookDao(new BookDaoMock());
        bookRepository = BookIoc.getBookRepository();
    }

    @DisplayName("Test find book List")
    @Test
    public void testFindAll(){
        List<Book> actualBookList = bookRepository.findAll();
        List<Book> expectedBookList = List.of(
                new Book(1, "9788433920423", "La conjura de los necios", null, null, null),
                new Book(2, "9788426418197", "El nombre de la rosa", null, null, null),
                new Book(3, "9786074213485", "La insoportable levedad del ser", null, null, null),
                new Book(4, "9788466338141", "La isla del día de antes", null, null, null),
                new Book(5, "9788448022440", "Buenos presagios", null, null, null)
        );
        assertEquals(actualBookList, expectedBookList, "Libro incorrecto");
    }

    @DisplayName("Test find book by id")
    @Test
    public void testFindById(){
        Book actualBook = bookRepository.findById(1);
        Book expectedBook = new Book(1, "9788433920423", "La conjura de los necios", null, null, null);
        assertEquals(actualBook, expectedBook, "Libro incorrecto");
    }

    @DisplayName("Test find book by non-existent id")
    @Test
    public void testFindByIdNonExistent() {
        Book actualBook = bookRepository.findById(6);
        assertNull(actualBook, "Libro encontrado");
    }
}
