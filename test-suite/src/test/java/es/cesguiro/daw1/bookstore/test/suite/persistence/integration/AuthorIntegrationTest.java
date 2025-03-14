package es.cesguiro.daw1.bookstore.test.suite.persistence.integration;

import es.cesguiro.daw1.bookstore.domain.model.Author;
import es.cesguiro.daw1.bookstore.domain.repository.AuthorRepository;
import es.cesguiro.daw1.bookstore.persistence.dao.AuthorDao;
import es.cesguiro.daw1.bookstore.persistence.dao.jdbc.AuthorDaoJdbc;
import es.cesguiro.daw1.bookstore.persistence.repository.AuthorRepositoryJdbc;
import es.cesguiro.daw1.bookstore.test.suite.AuthorsDataLoader;
import es.cesguiro.daw1.bookstore.test.suite.persistence.FlywayJdbcTest;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AuthorIntegrationTest extends FlywayJdbcTest {

    private final AuthorDao authorDao = new AuthorDaoJdbc();
    private final AuthorRepository authorRepository = new AuthorRepositoryJdbc(authorDao);
    private static List<Author> authors;

    @BeforeAll
    static void beforeAll() {
        AuthorsDataLoader loader = new AuthorsDataLoader();
        authors = loader.loadAuthorsFromCSV();
    }

    static Stream<Arguments> provideFindAllByBookIsbnArguments() {
        return Stream.of(
                Arguments.of("9780142424179", List.of(authors.getFirst())),
                Arguments.of("9780060557912", List.of(authors.get(15), authors.get(16))),
                Arguments.of("1234567890", List.of())
        );
    }


    @ParameterizedTest
    @MethodSource("provideFindAllByBookIsbnArguments")
    @DisplayName("Test findAllByBookIsbn method returns a list of AuthorEntity")
    void testFindAllByBookIsbn(String isbn, List<Author> expected) {
        List<Author> result = authorRepository.findAllByBookIsbn(isbn);

        if (expected.isEmpty()) {
            assertEquals(0, result.size());
            return;
        }

        assertAll(
                () -> assertEquals(expected.size(), result.size(), "Size should match"),
                () -> assertEquals(expected.getFirst().getName(), result.getFirst().getName(), "First name should match"),
                () -> assertEquals(expected.getFirst().getBiography("en"), result.getFirst().getBiography("en"), "First biographyEn should match"),
                () -> assertEquals(expected.getFirst().getBiography("es"), result.getFirst().getBiography("es"), "First biographyEs should match"),
                () -> assertEquals(expected.getFirst().getSlug(), result.getFirst().getSlug(), "First slug should match"),
                () -> assertEquals(expected.getLast().getName(), result.getLast().getName(), "Last name should match"),
                () -> assertEquals(expected.getLast().getBiography("es"), result.getLast().getBiography("es"), "Last biographyEn should match"),
                () -> assertEquals(expected.getLast().getBiography("en"), result.getLast().getBiography("en"), "Last biographyEs should match"),
                () -> assertEquals(expected.getLast().getSlug(), result.getLast().getSlug(), "Last slug should match")
        );
    }


}
