package es.cesguiro.daw1.bookstore.web.factory;

import es.cesguiro.daw1.bookstore.web.controller.Controller;
import es.cesguiro.daw1.bookstore.web.controller.MainController;

public class MainFactory {

    private static Controller mainController;

    public static Controller mainController() {
        if (mainController == null) {
            mainController = new MainController();
        }
        return mainController;
    }
}
