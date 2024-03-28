package es.cesguiro.daw1bookstore.common.language.impl;

import es.cesguiro.daw1bookstore.common.language.LanguageManager;

import java.util.Arrays;

public class LanguageManagerUrl implements LanguageManager {

    private String[] supportedLanguages = {"es", "en"};

    private String currentLanguage = "es";

    public String getCurrentLanguage() {
        return currentLanguage;
    }

    public void setCurrentLanguage(String language) {
        if (Arrays.asList(supportedLanguages).contains(language)) {
            currentLanguage = language;
        }
        currentLanguage = language;
    }
}
