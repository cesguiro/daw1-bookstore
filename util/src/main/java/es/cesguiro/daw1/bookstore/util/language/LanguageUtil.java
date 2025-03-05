package es.cesguiro.daw1.bookstore.util.language;

import es.cesguiro.daw1.bookstore.util.property.PropertyUtil;

import java.util.Arrays;
import java.util.List;

public class LanguageUtil {

    private final static String DEFAULT_LANGUAGE = "es";
    private final static String DEFAULT_LANGUAGE_PROPERTY = "app.language.default";
    private final static String SUPPORTED_LANGUAGES_PROPERTY = "app.language.supported";

    public static String getAllowLanguage(String lang) {
        String defaultLanguage = PropertyUtil.getPropertyProvider().getProperty(DEFAULT_LANGUAGE_PROPERTY, DEFAULT_LANGUAGE);
        String supportedLanguagesConfig = PropertyUtil.getPropertyProvider().getProperty(SUPPORTED_LANGUAGES_PROPERTY, defaultLanguage);
        List<String> supportedLanguages = Arrays.asList(supportedLanguagesConfig.split(","));
        if (supportedLanguages.contains(lang)) {
            return lang;
        }
        return defaultLanguage;
    }
}
