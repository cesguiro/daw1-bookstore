package es.cesguiro.daw1.bookstore.test.suite.persistence.integration;

import es.cesguiro.daw1.bookstore.domain.model.Author;
import es.cesguiro.daw1.bookstore.domain.repository.AuthorRepository;
import es.cesguiro.daw1.bookstore.persistence.dao.AuthorDao;
import es.cesguiro.daw1.bookstore.persistence.dao.jdbc.model.AuthorRecord;
import es.cesguiro.daw1.bookstore.persistence.repository.AuthorRepositoryJdbc;
import es.cesguiro.daw1.bookstore.test.suite.persistence.template.AuthorTestTemplateProvider;
import es.cesguiro.daw1.bookstore.util.context.RequestContext;
import es.cesguiro.daw1.bookstore.util.context.RequestContextHolder;
import es.cesguiro.daw1.bookstore.util.property.DefaultPropertyProvider;
import es.cesguiro.daw1.bookstore.util.property.PropertyUtil;
import org.flywaydb.core.Flyway;
import org.h2.jdbcx.JdbcDataSource;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;

import java.io.File;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(AuthorTestTemplateProvider.class)
public class PruebaTest {

    @BeforeAll
    static void beforeAll() throws SQLException {
        String projectBaseDir = new File(System.getProperty("user.dir")).getParent();
        String testPropertiesFile = projectBaseDir + "/config/test.properties";
        System.setProperty("app.properties.location", testPropertiesFile);
        PropertyUtil.loadPropertyFiles();

        String url = PropertyUtil.getPropertyProvider().getProperty("app.datasource.url");
        String username = PropertyUtil.getPropertyProvider().getProperty("app.datasource.username");
        String password = PropertyUtil.getPropertyProvider().getProperty("app.datasource.password");

        JdbcDataSource dataSource = new JdbcDataSource();
        dataSource.setURL(url);
        dataSource.setUser(username);
        dataSource.setPassword(password);
        RequestContext requestContext = new RequestContext();
        RequestContextHolder.setRequestContext(requestContext);
        RequestContextHolder.getRequestContext().setConnection(dataSource.getConnection());


        Flyway flyway = Flyway.configure()
                .dataSource(dataSource)
                .locations(PropertyUtil.getPropertyProvider().getProperty("app.flyway.locations"))
                .cleanDisabled(PropertyUtil.getPropertyProvider().getBooleanProperty("app.flyway.cleanDisabled", false))
                .load();
        flyway.clean();
        flyway.migrate();
    }

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
