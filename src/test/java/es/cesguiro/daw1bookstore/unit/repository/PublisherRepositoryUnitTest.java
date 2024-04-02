package es.cesguiro.daw1bookstore.unit.repository;

import es.cesguiro.daw1bookstore.common.container.PublisherIoc;
import es.cesguiro.daw1bookstore.domain.model.Publisher;
import es.cesguiro.daw1bookstore.mock.dao.PublisherDaoMock;
import es.cesguiro.daw1bookstore.persistence.repository.PublisherRepository;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@DisplayName("PublisherRepository Unit Tests")
public class PublisherRepositoryUnitTest {

    private static PublisherRepository publisherRepository;

    @BeforeAll
    public static void setUp() {
        PublisherIoc.setPublisherDao(new PublisherDaoMock());
        publisherRepository = PublisherIoc.getPublisherRepository();
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
        assertNull(actualPublisher, "Editorial encontrada");
    }

    @AfterAll
    public static void tearDown() {
        PublisherIoc.reset();
    }
}
