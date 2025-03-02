package es.cesguiro.daw1.bookstore.test.suite.persistence.jdbc;

import es.cesguiro.daw1.bookstore.persistence.dao.PublisherDao;
import es.cesguiro.daw1.bookstore.persistence.dao.jdbc.PublisherDaoJdbc;
import es.cesguiro.daw1.bookstore.persistence.dao.jdbc.model.PublisherRecord;
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

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class PublisherDaoJdbcTest extends FlywayJdbcTest {

    private final PublisherDao publisherDao = new PublisherDaoJdbc();
    private static List<PublisherRecord> publishersRecord;

    @BeforeAll
    static void beforeAll() {
        PublishersDataLoader loader = new PublishersDataLoader();
        publishersRecord = loader.loadPublisherRecordsFromCSV();
    }

    static Stream<Arguments> provideFindPublisherByBookIsbnArguments() {
        return Stream.of(
                Arguments.of("9780142424179", publishersRecord.get(0)),
                Arguments.of("9780060557912", publishersRecord.get(4)),
                Arguments.of("1234567890", null)
        );
    }

    @ParameterizedTest(name = "Find publisher by book with ISBN {0}")
    @MethodSource("provideFindPublisherByBookIsbnArguments")
    @DisplayName("Find publisher by book with ISBN")
    void findPublisherByBookIsbn(String isbn, PublisherRecord expectedPublisher) {
        Optional<PublisherRecord> result = publisherDao.findByBookIsbn(isbn);

        if (expectedPublisher == null) {
            assertEquals(Optional.empty(), result);
        } else {
            assertAll(
                    () -> assertEquals(expectedPublisher.id(), result.get().id(), "Id should match"),
                    () -> assertEquals(expectedPublisher.name(), result.get().name(), "Name should match"),
                    () -> assertEquals(expectedPublisher.slug(), result.get().slug(), "Slug should match")
            );
        }

    }



}