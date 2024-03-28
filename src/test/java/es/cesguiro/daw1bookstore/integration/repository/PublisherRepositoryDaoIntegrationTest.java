package es.cesguiro.daw1bookstore.integration.repository;

import es.cesguiro.daw1bookstore.common.container.PublisherIoc;
import es.cesguiro.daw1bookstore.domain.model.Publisher;
import es.cesguiro.daw1bookstore.persistence.repository.PublisherRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Publisher Repository-DAO Integration Test")
public class PublisherRepositoryDaoIntegrationTest {

    private static final PublisherRepository publisherRepository = PublisherIoc.getPublisherRepository();

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
