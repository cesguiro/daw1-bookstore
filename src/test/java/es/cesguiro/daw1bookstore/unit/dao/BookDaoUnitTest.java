package es.cesguiro.daw1bookstore.unit.dao;

import es.cesguiro.daw1bookstore.common.container.BookIoc;
import es.cesguiro.daw1bookstore.domain.model.Author;
import es.cesguiro.daw1bookstore.domain.model.Book;
import es.cesguiro.daw1bookstore.domain.model.Publisher;
import es.cesguiro.daw1bookstore.persistence.dao.BookDao;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.i18n.LocaleContextHolder;

import java.math.BigDecimal;
import java.util.List;
import java.util.Locale;
import java.util.logging.LogManager;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("BookDao Unit Tests")
public class BookDaoUnitTest {

    private final BookDao bookDao = BookIoc.getBookDao();

    @DisplayName("Test find All books")
    @Test
    public void testFindAllBooks() {
        List<Book> actualBookList = bookDao.findAll();
        Book expectedBook = new Book(
                1,
                "9788433920423",
                "La conjura de los necios",
                "El protagonista de esta novela es uno de los personajes más memorables...",
                new BigDecimal(13.20),
                "necios.jpeg"
        );

        assertAll("books",
                () -> assertEquals(5, actualBookList.size(), "Tamaño del listado incorrecto"),
                () -> assertEquals(expectedBook, actualBookList.get(0), "Libro incorrecto")
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
                () -> assertEquals(expectedBook, actualBook, "Libro incorrecto"),
                () -> assertEquals(expectedBook.getPublisher(), actualBook.getPublisher(), "Editor incorrecto"),
                () -> assertEquals(expectedBook.getAuthorList(), actualBook.getAuthorList(), "Lista de autores incorrecta")
        );
    }

    @DisplayName("Test find book by non-existent id")
    @Test
    public void testFindBookByIdNotFound() {
        Book actualBook = bookDao.findById(10);
        assertNull(actualBook, "Libro encontrado");
    }

    @DisplayName("Test find book in english")
    @Test
    public void testFindBookInEnglish() {
        LocaleContextHolder.setLocale(new Locale("en"));
        Book actualBook = bookDao.findById(2);
        Book expectedBook = new Book(
                2,
                "9788426418197",
                "The name of the rose",
                "The year is 1327. Franciscans in a wealthy Italian abbey are suspected of heresy, and Brother William of Baskerville arrives to investigate...",
                new BigDecimal(12.30),
                "nombreRosa.jpeg",
                new Publisher(2, "Penguin Random House Grupo Editorial España"),
                List.of(new Author(2, "Umberto Eco"))
        );
        assertAll(
                () -> assertEquals(expectedBook.getTitle(), actualBook.getTitle(), "Título incorrecto"),
                () -> assertEquals(expectedBook.getSynopsis(), actualBook.getSynopsis(), "Sinopsis incorrecta")
        );
    }

    @AfterEach
    public void tearDown() {
        LocaleContextHolder.resetLocaleContext();
    }
}
