package es.cesguiro.daw1bookstore.integration;

import es.cesguiro.daw1bookstore.common.AppPropertiesReader;
import es.cesguiro.daw1bookstore.common.container.BookIoc;
import es.cesguiro.daw1bookstore.common.exception.ResourceNotFoundException;
import es.cesguiro.daw1bookstore.domain.model.Book;
import es.cesguiro.daw1bookstore.domain.service.BookService;
import es.cesguiro.daw1bookstore.persistence.dao.impl.jdbc.rawSql.RawSql;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Book Integration Test")
public class BookIntegrationTest {

    private final BookService bookService = BookIoc.getBookService();

    @BeforeAll
    public static void setupAll(){
        // Configuración de Flyway
        Flyway flyway = Flyway.configure().dataSource(
                AppPropertiesReader.getProperty("flyway.url"),
                AppPropertiesReader.getProperty("flyway.user"),
                AppPropertiesReader.getProperty("flyway.password")
        ).load();

        // Ejecución de migraciones
        flyway.migrate();
    }

    @AfterEach
    public void teardown(){
        RawSql.rollback();
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
