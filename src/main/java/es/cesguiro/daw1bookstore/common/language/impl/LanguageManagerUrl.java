package es.cesguiro.daw1bookstore.common.language.impl;

import es.cesguiro.daw1bookstore.common.language.LanguageManager;

import java.util.Arrays;

public class LanguageManagerUrl implements LanguageManager {

    private String[] supportedLanguages = {"es", "en"};

    private String currentLanguage;

    private String defaultLanguage = "es";

    public String getCurrentLanguage() {
        return currentLanguage;
    }

    public void setCurrentLanguage(String language) {
        if(language == null) {
            language = defaultLanguage;
        }
        if (Arrays.asList(supportedLanguages).contains(language)) {
            currentLanguage = language;
        } else {
            currentLanguage = defaultLanguage;
        }
    }
}
