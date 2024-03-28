package es.cesguiro.daw1bookstore.common.container;

import es.cesguiro.daw1bookstore.common.language.LanguageManager;
import es.cesguiro.daw1bookstore.common.language.impl.LanguageManagerUrl;
import org.springframework.stereotype.Component;

public class LanguageIoc {

    private static LanguageManager languageManager;

    public static LanguageManager getLanguageManager() {
        if (languageManager == null) {
            languageManager = new LanguageManagerUrl();
        }
        return languageManager;
    }
}
