package es.cesguiro.daw1.bookstore.test.suite.domain.model;

import es.cesguiro.daw1.bookstore.domain.model.Author;
import es.cesguiro.daw1.bookstore.test.suite.AuthorsDataLoader;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AuthorTest {

    private static List<Author> authors;

    @BeforeAll
    static void setUp() {
        AuthorsDataLoader loader = new AuthorsDataLoader();
        authors = loader.loadAuthorsFromCSV();
    }

    static Stream<Arguments> provideGetBiographyArguments() {
        return Stream.of(
                Arguments.of(authors.get(1).getBiography("es"), "es"),
                Arguments.of(authors.get(1).getBiography("en"), "en"),
                Arguments.of(authors.get(1).getBiography("es"), "fr")
        );
    }


    @ParameterizedTest
    @MethodSource("provideGetBiographyArguments")
    @DisplayName("Test getBiography should return biography according to locale")
    void testGetBiography(String expected, String locale) {
        String actual = authors.get(1).getBiography(locale);
        assertEquals(expected, actual, "Biography should match");
    }

}