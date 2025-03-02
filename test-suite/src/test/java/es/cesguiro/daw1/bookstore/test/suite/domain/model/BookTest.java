package es.cesguiro.daw1.bookstore.test.suite.domain.model;

import es.cesguiro.daw1.bookstore.domain.model.Book;
import es.cesguiro.daw1.bookstore.test.suite.BooksDataLoader;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BookTest{

    private static List<Book> books;

    @BeforeAll
    static void setUp() {
        BooksDataLoader loader = new BooksDataLoader();
        books = loader.loadBooksFromCSV();
    }

    static Stream<Arguments> ProvideGetTitleArguments() {
        return Stream.of(
                Arguments.of(books.get(4).getTitle("es"), "es"),
                Arguments.of(books.get(4).getTitle("en"), "en"),
                Arguments.of(books.get(4).getTitle("es"), "fr")
        );
    }

    @ParameterizedTest
    @MethodSource("ProvideGetTitleArguments")
    @DisplayName("Test get title should return title according to locale")
    void testGetTitleInSpanish(String expected, String locale) {
        String actual = books.get(4).getTitle(locale);
        assertEquals(expected, actual, "Titles should match");
    }

    static Stream<Arguments> ProvideGetSynopsisArguments() {
        return Stream.of(
                Arguments.of(books.get(4).getSynopsis("es"), "es"),
                Arguments.of(books.get(4).getSynopsis("en"), "en"),
                Arguments.of(books.get(4).getSynopsis("es"), "fr")
        );
    }

    @ParameterizedTest
    @MethodSource("ProvideGetSynopsisArguments")
    @DisplayName("Test get synopsis should return synopsis according to locale")
    void testGetSynopsisInSpanish(String expected, String locale) {
        String actual = books.get(4).getSynopsis(locale);
        assertEquals(expected, actual, "Synopses should match");
    }

    @ParameterizedTest
    @CsvSource({
            "0, 14.39",
            "1, 14.24",
            "7, 11.99"
    })
    @DisplayName("Test get base price should return base price")
    void testDiscountPrice(int index, String expected) {
        BigDecimal actual = books.get(index).calculateFinalPrice();
        assertEquals(new BigDecimal(expected), actual, "Final  prices should match");
    }

}