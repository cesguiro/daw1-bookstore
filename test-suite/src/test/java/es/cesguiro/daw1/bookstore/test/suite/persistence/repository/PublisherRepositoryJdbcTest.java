package es.cesguiro.daw1.bookstore.test.suite.persistence.repository;

import es.cesguiro.daw1.bookstore.domain.model.Publisher;
import es.cesguiro.daw1.bookstore.persistence.dao.PublisherDao;
import es.cesguiro.daw1.bookstore.persistence.dao.jdbc.model.PublisherRecord;
import es.cesguiro.daw1.bookstore.persistence.repository.PublisherRepositoryJdbc;
import es.cesguiro.daw1.bookstore.test.suite.PublishersDataLoader;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PublisherRepositoryJdbcTest {

    private static List<Publisher> publishers;
    private static List<PublisherRecord> publisherRecords;

    @Mock
    PublisherDao publisherDao;
    @InjectMocks
    PublisherRepositoryJdbc publisherRepositoryJdbc;

    @BeforeAll
    static void setUp() {
        PublishersDataLoader loader = new PublishersDataLoader();
        publishers = loader.loadPublishersFromCSV();
        publisherRecords = loader.loadPublisherRecordsFromCSV();
    }

    static Stream<Arguments> provideFindAllByBookIsbnArguments() {
        return Stream.of(
                Arguments.of("9780142424179", Optional.of(publisherRecords.getFirst()), Optional.of(publishers.getFirst())),
                Arguments.of("1234567890", Optional.empty(), Optional.empty())
        );
    }

    @ParameterizedTest
    @MethodSource("provideFindAllByBookIsbnArguments")
    @DisplayName("Test findByBookIsbn method returns Optional<PublisherEntity>")
    void testFindAll(String isbn, Optional<PublisherRecord> optionalPublisherRecord, Optional<Publisher> expected) {
        when(publisherDao.findByBookIsbn(isbn)).thenReturn(Optional.ofNullable(optionalPublisherRecord.orElse(null)));
        Optional<Publisher> result = publisherRepositoryJdbc.findByBookIsbn(isbn);

        if (expected.isEmpty()) {
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