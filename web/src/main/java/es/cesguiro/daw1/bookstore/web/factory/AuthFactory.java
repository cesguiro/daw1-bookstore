package es.cesguiro.daw1.bookstore.web.factory;

import es.cesguiro.daw1.bookstore.application.handler.AuthHandler;
import es.cesguiro.daw1.bookstore.application.handler.impl.AuthHandlerImpl;
import es.cesguiro.daw1.bookstore.web.controller.AuthController;
import es.cesguiro.daw1.bookstore.web.controller.Controller;

public class AuthFactory {

    private static Controller authController;
    private static AuthHandler authHandler;

    public static Controller authController() {
        if (authController == null) {
            authController = new AuthController(authHandler());
        }
        return authController;
    }

    public static AuthHandler authHandler() {
        if (authHandler == null) {
            authHandler = new AuthHandlerImpl(UserFactory.insertUserUseCase());
        }
        return authHandler;
    }
}
