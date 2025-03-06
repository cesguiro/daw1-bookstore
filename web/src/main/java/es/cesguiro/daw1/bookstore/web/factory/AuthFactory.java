package es.cesguiro.daw1.bookstore.web.factory;

import es.cesguiro.daw1.bookstore.application.handler.AuthHandler;
import es.cesguiro.daw1.bookstore.application.handler.impl.AuthHandlerImpl;
import es.cesguiro.daw1.bookstore.domain.service.auth.LoginService;
import es.cesguiro.daw1.bookstore.domain.service.auth.StoreTokenService;
import es.cesguiro.daw1.bookstore.domain.usecase.auth.LoginUseCase;
import es.cesguiro.daw1.bookstore.domain.usecase.auth.StoreTokenUseCase;
import es.cesguiro.daw1.bookstore.web.controller.AuthController;
import es.cesguiro.daw1.bookstore.web.controller.Controller;

import java.util.Optional;

public class AuthFactory {

    private static Controller authController;
    private static AuthHandler authHandler;
    private static LoginUseCase loginUseCase;
    private static StoreTokenUseCase storeTokenUseCase;

    public static Controller authController() {
        if (authController == null) {
            authController = new AuthController(authHandler());
        }
        return authController;
    }

    public static AuthHandler authHandler() {
        if (authHandler == null) {
            authHandler = new AuthHandlerImpl(loginUseCase(), storeTokenUseCase());
        }
        return authHandler;
    }

    public static LoginUseCase loginUseCase() {
        if (loginUseCase == null) {
            loginUseCase = new LoginService(UserFactory.userRepository());
        }
        return loginUseCase;
    }

    public static StoreTokenUseCase storeTokenUseCase() {
        if (storeTokenUseCase == null) {
            storeTokenUseCase = new StoreTokenService(UserFactory.userRepository());
        }
        return storeTokenUseCase;
    }
}
