package es.cesguiro.daw1bookstore.unit.dao;

import es.cesguiro.daw1bookstore.common.AppPropertiesReader;
import es.cesguiro.daw1bookstore.common.container.PublisherIoc;
import es.cesguiro.daw1bookstore.domain.model.Publisher;
import es.cesguiro.daw1bookstore.persistence.dao.PublisherDao;
import es.cesguiro.daw1bookstore.persistence.dao.impl.jdbc.rawSql.RawSql;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("PublisherDao Unit Tests")
public class PublisherDaoUnitTest {

    private PublisherDao publisherDao = PublisherIoc.getPublisherDao();

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

    @DisplayName("Test find publisher by book id")
    @Test
    public void testFindByBookId() {
        Publisher actualPublisher = publisherDao.findByBookId(1);
        Publisher expectedPublisher = new Publisher(1, "Editorial Anagrama");
        assertEquals(expectedPublisher, actualPublisher, "Editorial incorrecta");
    }

    @DisplayName("Test find publisher by not existing book id")
    @Test
    public void testFindByNotExistingBookId() {
        Publisher actualPublisher = publisherDao.findByBookId(100);
        assertEquals(null, actualPublisher, "Editorial incorrecta");
    }
}
