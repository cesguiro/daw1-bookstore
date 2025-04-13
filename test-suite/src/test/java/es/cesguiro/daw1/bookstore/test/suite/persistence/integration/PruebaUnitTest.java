package es.cesguiro.daw1.bookstore.test.suite.persistence.integration;

import es.cesguiro.daw1.bookstore.domain.model.Author;
import es.cesguiro.daw1.bookstore.domain.repository.AuthorRepository;
import es.cesguiro.daw1.bookstore.persistence.dao.AuthorDao;
import es.cesguiro.daw1.bookstore.persistence.repository.AuthorRepositoryJdbc;
import es.cesguiro.daw1.bookstore.test.suite.persistence.FlywayJdbcTest;
import es.cesguiro.daw1.bookstore.test.suite.persistence.template.AuthorTestTemplateProvider;
import es.cesguiro.daw1.bookstore.util.context.RequestContext;
import es.cesguiro.daw1.bookstore.util.context.RequestContextHolder;
import es.cesguiro.daw1.bookstore.util.property.PropertyUtil;
import org.flywaydb.core.Flyway;
import org.h2.jdbcx.JdbcDataSource;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.ExtendWith;

import java.io.File;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Tag("Unit")
@ExtendWith(AuthorTestTemplateProvider.class)
public class PruebaUnitTest extends FlywayJdbcTest {

    @TestTemplate
    void testFindAuthorsByBookIsbn(AuthorDao authorDao, String isbn, List<Author> expected) throws SQLException {
        AuthorRepository authorRepository = new AuthorRepositoryJdbc(authorDao);

        // 🔹 Ejecutamos la prueba
        List<Author> actual = authorRepository.findAllByBookIsbn(isbn);

        // 🔹 Validamos la respuesta
        if (expected.isEmpty()) {
            assertTrue(actual.isEmpty(), "No deberían existir autores para ISBN: " + isbn);
        } else {
            assertFalse(actual.isEmpty(), "Los autores deberían existir para ISBN: " + isbn);
            assertEquals(expected.size(), actual.size(), "Número de autores incorrecto");
        }
    }
}
