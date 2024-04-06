package es.cesguiro.daw1bookstore.integration.repository;

import es.cesguiro.daw1bookstore.common.AppPropertiesReader;
import es.cesguiro.daw1bookstore.common.container.PublisherIoc;
import es.cesguiro.daw1bookstore.domain.model.Publisher;
import es.cesguiro.daw1bookstore.persistence.dao.impl.jdbc.rawSql.RawSql;
import es.cesguiro.daw1bookstore.persistence.repository.PublisherRepository;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Publisher Repository-DAO Integration Test")
public class PublisherRepositoryDaoIntegrationTest {

    private static final PublisherRepository publisherRepository = PublisherIoc.getPublisherRepository();

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
    public void testFindByBookId(){
        Publisher actualPublisher = publisherRepository.findByBookId(1);
        Publisher expectedPublisher = new Publisher(1, "Anagrama");
        assertEquals(expectedPublisher, actualPublisher, "Editorial incorrecta");
    }

    @DisplayName("Test find publisher by non-existent book id")
    @Test
    public void testFindByBookIdNonExistent() {
        Publisher actualPublisher = publisherRepository.findByBookId(6);
        assertEquals(null, actualPublisher, "Editorial encontrada");
    }
}
