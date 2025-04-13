package es.cesguiro.daw1.bookstore.test.suite.persistence.jdbc;

import es.cesguiro.daw1.bookstore.persistence.dao.AuthorDao;
import es.cesguiro.daw1.bookstore.persistence.dao.jdbc.AuthorDaoJdbc;
import es.cesguiro.daw1.bookstore.persistence.dao.jdbc.model.AuthorRecord;
import es.cesguiro.daw1.bookstore.test.suite.AuthorsDataLoader;
import es.cesguiro.daw1.bookstore.test.suite.persistence.FlywayJdbcTest;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class AuthorDaoJdbcTest extends FlywayJdbcTest {

    private final AuthorDao authorDao = new AuthorDaoJdbc();
    private static List<AuthorRecord> authorsRecord;

    @BeforeAll
    static void beforeAll() throws URISyntaxException {
        AuthorsDataLoader loader = new AuthorsDataLoader();
        authorsRecord = loader.loadAuthorRecordsFromCSV();
    }

    static Stream<Arguments> provideFindAuthorsByBookIsbnArguments() {
        return Stream.of(
                Arguments.of("9780142424179", List.of(authorsRecord.getFirst())),
                Arguments.of("9780060557912", List.of(authorsRecord.get(15), authorsRecord.get(16))),
                Arguments.of("1234567890", List.of())
        );
    }

    @ParameterizedTest(name = "Find all authors by book with ISBN {0}")
    @MethodSource("provideFindAuthorsByBookIsbnArguments")
    @DisplayName("Find all authors by book with ISBN")
    void findAllAuthorsByBookWithIsbn(String isbn, List<AuthorRecord> expectedAuthors) {
        List<AuthorRecord> result = authorDao.findAllByBookIsbn(isbn);

        assertEquals(expectedAuthors.size(), result.size());

        for (int i = 0; i < expectedAuthors.size(); i++) {
            AuthorRecord expectedAuthor = expectedAuthors.get(i);
            AuthorRecord resultAuthor = result.get(i);
            assertAll(
                    () -> assertEquals(expectedAuthor.id(), resultAuthor.id(), "Id should match"),
                    () -> assertEquals(expectedAuthor.name(), resultAuthor.name(), "Name should match"),
                    () -> assertEquals(expectedAuthor.nationality(), resultAuthor.nationality(), "Nationality should match"),
                    () -> assertEquals(expectedAuthor.biographyEs(), resultAuthor.biographyEs(), "BiographyEs should match"),
                    () -> assertEquals(expectedAuthor.biographyEn(), resultAuthor.biographyEn(), "BiographyEn should match"),
                    () -> assertEquals(expectedAuthor.birthYear(), resultAuthor.birthYear(), "BirthYear should match"),
                    () -> assertEquals(expectedAuthor.deathYear(), resultAuthor.deathYear(), "DeathYear should match"),
                    () -> assertEquals(expectedAuthor.slug(), resultAuthor.slug(), "Slug should match")
            );
        }
    }


}
