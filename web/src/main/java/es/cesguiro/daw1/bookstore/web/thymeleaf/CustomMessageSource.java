package es.cesguiro.daw1.bookstore.web.thymeleaf;

import es.cesguiro.daw1.bookstore.util.context.RequestContextHolder;

import java.util.Locale;
import java.util.ResourceBundle;

public class CustomMessageSource {

    private static final String BASE_NAME = "messages";

    public static String getMessage(String key) {
        Locale locale = RequestContextHolder.getRequestContext().getLocale();
        ResourceBundle bundle = ResourceBundle.getBundle(BASE_NAME, locale);

        if (bundle.containsKey(key)) {
            return bundle.getString(key);
        } else {
            return key;
        }
    }
}
