package es.cesguiro.daw1.bookstore.web.factory;

import es.cesguiro.daw1.bookstore.web.controller.Controller;
import es.cesguiro.daw1.bookstore.web.controller.LocaleController;

public class LocaleFactory {

    private static Controller localeController;

    public static Controller createLocaleController() {
        if (localeController == null) {
            localeController = new LocaleController();
        }
        return localeController;
    }
}
