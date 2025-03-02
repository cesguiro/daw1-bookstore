package es.cesguiro.daw1.bookstore.test.suite.persistence.integration;

import es.cesguiro.daw1.bookstore.domain.model.Publisher;
import es.cesguiro.daw1.bookstore.domain.repository.PublisherRepository;
import es.cesguiro.daw1.bookstore.persistence.dao.PublisherDao;
import es.cesguiro.daw1.bookstore.persistence.dao.jdbc.PublisherDaoJdbc;
import es.cesguiro.daw1.bookstore.persistence.repository.PublisherRepositoryJdbc;
import es.cesguiro.daw1.bookstore.test.suite.PublishersDataLoader;
import es.cesguiro.daw1.bookstore.test.suite.persistence.FlywayJdbcTest;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class PublisherIntegrationTest extends FlywayJdbcTest {

    private final PublisherDao publisherDao = new PublisherDaoJdbc();
    private final PublisherRepository publisherRepository = new PublisherRepositoryJdbc(publisherDao);

    private static List<Publisher> publishers;

    @BeforeAll
    static void beforeAll() {
        PublishersDataLoader loader = new PublishersDataLoader();
        publishers = loader.loadPublishersFromCSV();
    }

    static Stream<Arguments> provideFindPublisherByBookIsbnArguments() {
        return Stream.of(
                Arguments.of("9780142424179", Optional.of(publishers.get(0))),
                Arguments.of("9780060557912", Optional.of(publishers.get(4))),
                Arguments.of("1234567890", null)
        );
    }

    @ParameterizedTest
    @MethodSource("provideFindPublisherByBookIsbnArguments")
    @DisplayName("Test findByBookIsbn method returns Optional<PublisherEntity>")
    void testFindByBookIsbn(String isbn, Optional<Publisher> expected) {
        Optional<Publisher> result = publisherRepository.findByBookIsbn(isbn);

        if (expected == null) {
            assertTrue(result.isEmpty());
            return;
        }

        assertAll(
                () -> assertTrue(result.isPresent()),
                () -> assertEquals(expected.get().getName(), result.get().getName()),
                () -> assertEquals(expected.get().getSlug(), result.get().getSlug())
        );
    }

}
