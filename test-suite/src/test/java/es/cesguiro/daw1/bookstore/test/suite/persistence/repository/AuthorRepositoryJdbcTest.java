package es.cesguiro.daw1.bookstore.test.suite.persistence.repository;

import es.cesguiro.daw1.bookstore.domain.model.Author;
import es.cesguiro.daw1.bookstore.persistence.dao.AuthorDao;
import es.cesguiro.daw1.bookstore.persistence.dao.jdbc.model.AuthorRecord;
import es.cesguiro.daw1.bookstore.persistence.repository.AuthorRepositoryJdbc;
import es.cesguiro.daw1.bookstore.test.suite.AuthorsDataLoader;
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
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AuthorRepositoryJdbcTest {

    private static List<Author> authors;
    private static List<AuthorRecord> authorRecords;

    @Mock
    AuthorDao authorDao;
    @InjectMocks
    AuthorRepositoryJdbc authorRepositoryJdbc;

    @BeforeAll
    static void setUp() {
        AuthorsDataLoader loader = new AuthorsDataLoader();
        authors = loader.loadAuthorsFromCSV();
        authorRecords = loader.loadAuthorRecordsFromCSV();
    }

    static Stream<Arguments> provideFindAllByBookIsbnArguments() {
        return Stream.of(
                Arguments.of("9780142424179", List.of(authorRecords.get(0), authorRecords.get(1)), List.of(authors.get(0), authors.get(1))),
                Arguments.of("9780060557912", List.of(authorRecords.get(0)), List.of(authors.get(0))),
                Arguments.of("1234567890", List.of(), List.of())
        );
    }

    @ParameterizedTest
    @MethodSource("provideFindAllByBookIsbnArguments")
    @DisplayName("Test findAllByBookIsbn method returns a list of AuthorEntity")
    void testFindAllByBookIsbn(String isbn, List<AuthorRecord> authorRecords, List<Author> expected) {
        when(authorDao.findAllByBookIsbn(isbn)).thenReturn(authorRecords);

        List<Author> result = authorRepositoryJdbc.findAllByBookIsbn(isbn);

        if (expected.isEmpty()) {
            assertEquals(0, result.size());
            return;
        }

        assertAll(
                () -> assertEquals(expected.size(), result.size()),
                () -> assertEquals(expected.getFirst().getName(), result.getFirst().getName()),
                () -> assertEquals(expected.getFirst().getBiography("en"), result.getFirst().getBiography("en")),
                () -> assertEquals(expected.getFirst().getBiography("es"), result.getFirst().getBiography("es")),
                () -> assertEquals(expected.getFirst().getSlug(), result.getFirst().getSlug()),
                () -> assertEquals(expected.getLast().getName(), result.getLast().getName()),
                () -> assertEquals(expected.getLast().getBiography("en"), result.getLast().getBiography("en")),
                () -> assertEquals(expected.getLast().getBiography("es"), result.getLast().getBiography("es")),
                () -> assertEquals(expected.getLast().getSlug(), result.getLast().getSlug())
        );
    }

}