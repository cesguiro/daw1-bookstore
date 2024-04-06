package es.cesguiro.daw1bookstore.unit.dao;

import es.cesguiro.daw1bookstore.common.AppPropertiesReader;
import es.cesguiro.daw1bookstore.common.container.AuthorIoc;
import es.cesguiro.daw1bookstore.domain.model.Author;
import es.cesguiro.daw1bookstore.persistence.dao.AuthorDao;
import es.cesguiro.daw1bookstore.persistence.dao.impl.jdbc.rawSql.RawSql;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("AuthorDao Unit Tests")
public class AuthorDaoUnitTest {

    AuthorDao authorDao = AuthorIoc.getAuthorDao();

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

    @DisplayName("Test find author list with only 1 author by book id")
    @Test
    public void testFindAuthorListByBookId() {
        List<Author> actualAuthorList = authorDao.findByBookId(1);
        List<Author> expectedAuthorList = List.of(new Author(1, "John Kennedy Toole"));
        assertEquals(expectedAuthorList, actualAuthorList, "Autor incorrecto");
    }

    @DisplayName("Test find author list with more than 1 author by book id")
    @Test
    public void testFindAurhotListMoreThanOneByBookId() {
        List<Author> actualAuthorList = authorDao.findByBookId(5);
        List<Author> expectedAuthorList = List.of(
                new Author(4, "Terry Pratchett"),
                new Author(5, "Neil Gaiman")
        );
        assertEquals(expectedAuthorList, actualAuthorList, "Autor incorrecto");
    }


    @DisplayName("Test find author list by not existing book id")
    @Test
    public void testFindByNotExistingBookId() {
        List<Author> actualAuthorList = authorDao.findByBookId(100);
        assertEquals(List.of(), actualAuthorList, "Autor incorrecto");
    }
}
