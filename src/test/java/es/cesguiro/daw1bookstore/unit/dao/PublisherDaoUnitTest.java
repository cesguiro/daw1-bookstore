package es.cesguiro.daw1bookstore.unit.dao;

import es.cesguiro.daw1bookstore.common.container.PublisherIoc;
import es.cesguiro.daw1bookstore.domain.model.Publisher;
import es.cesguiro.daw1bookstore.persistence.dao.PublisherDao;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("PublisherDao Unit Tests")
public class PublisherDaoUnitTest {

    private PublisherDao publisherDao = PublisherIoc.getPublisherDao();

    @DisplayName("Test find publisher by book id")
    @Test
    public void testFindByBookId() {
        Publisher actualPublisher = publisherDao.findByBookId(1);
        Publisher expectedPublisher = new Publisher(1, "Editorial Anagrama");
        assertEquals(actualPublisher, expectedPublisher, "Editorial incorrecta");
    }

    @DisplayName("Test find publisher by not existing book id")
    @Test
    public void testFindByNotExistingBookId() {
        Publisher actualPublisher = publisherDao.findByBookId(100);
        assertEquals(actualPublisher, null, "Editorial incorrecta");
    }
}
