package es.cesguiro.daw1.bookstore.test.suite.domain.model.vo;

import es.cesguiro.daw1.bookstore.domain.model.vo.LocaleString;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LocaleStringTest {

    @ParameterizedTest
    @CsvSource({
            "titleEs, titleEn, es, titleEs",
            "titleEs, titleEn, en, titleEn",
            "titleEs, titleEn, fr, titleEs"
    })
    @DisplayName("Test getValue method should return value according to locale")
    void testGetValue(String es, String en, String locale, String expected) {
        LocaleString localeString = new LocaleString(es, en);
        assertEquals(expected, localeString.getValue(locale));
    }

}