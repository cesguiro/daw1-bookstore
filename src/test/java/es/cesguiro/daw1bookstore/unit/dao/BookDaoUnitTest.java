package es.cesguiro.daw1bookstore.unit.dao;

import es.cesguiro.daw1bookstore.common.container.BookIoc;
import es.cesguiro.daw1bookstore.domain.model.Author;
import es.cesguiro.daw1bookstore.domain.model.Book;
import es.cesguiro.daw1bookstore.domain.model.Publisher;
import es.cesguiro.daw1bookstore.persistence.dao.BookDao;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("BookDao Unit Tests")
public class BookDaoUnitTest {

    private final BookDao bookDao = BookIoc.getBookDao();

    @DisplayName("Test find All books")
    @Test
    public void testFindAllBooks() {
        List<Book> actualBookList = bookDao.findAll();
        Book expectedBookList = new Book(
                1,
                "9788433920423",
                "La conjura de los necios",
                "El protagonista de esta novela es uno de los personajes más memorables...",
                new BigDecimal(13.20),
                "necios.jpeg"
        );

        assertAll("books",
                () -> assertEquals(actualBookList.size(), 5, "Tamaño del listado incorrecto"),
                () -> assertEquals(actualBookList.get(0), expectedBookList, "Libro incorrecto")
        );
    }

    @DisplayName("Test find book by id")
    @Test
    public void testFindBookById() {
        Book actualBook = bookDao.findById(1);
        Book expectedBook = new Book(
                1,
                "9788433920423",
                "La conjura de los necios",
                "El protagonista de esta novela es uno de los personajes más memorables...",
                new BigDecimal(13.20),
                "necios.jpeg",
                new Publisher(1, "Editorial Anagrama"),
                List.of(new Author(1, "John Kennedy Toole"))
        );

        assertAll("books",
                () -> assertEquals(actualBook, expectedBook, "Libro incorrecto"),
                () -> assertEquals(actualBook.getPublisher(), expectedBook.getPublisher(), "Editor incorrecto"),
                () -> assertEquals(actualBook.getAuthorList(), expectedBook.getAuthorList(), "Lista de autores incorrecta")
        );
    }

    @DisplayName("Test find book by non-existent id")
    @Test
    public void testFindBookByIdNotFound() {
        Book actualBook = bookDao.findById(10);
        assertNull(actualBook, "Libro encontrado");
    }
}
