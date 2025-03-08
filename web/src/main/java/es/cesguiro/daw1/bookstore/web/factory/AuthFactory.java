package es.cesguiro.daw1.bookstore.web.factory;

import es.cesguiro.daw1.bookstore.application.handler.AuthHandler;
import es.cesguiro.daw1.bookstore.application.handler.impl.AuthHandlerImpl;
import es.cesguiro.daw1.bookstore.domain.service.auth.LoginService;
import es.cesguiro.daw1.bookstore.domain.service.auth.StoreTokenService;
import es.cesguiro.daw1.bookstore.domain.usecase.auth.LoginUseCase;
import es.cesguiro.daw1.bookstore.domain.usecase.auth.StoreTokenUseCase;
import es.cesguiro.daw1.bookstore.web.controller.AuthController;
import es.cesguiro.daw1.bookstore.web.controller.Controller;

public class AuthFactory {

    private static Controller authController;
    private static AuthHandler authHandler;
    private static LoginUseCase loginUseCase;
    private static StoreTokenUseCase storeTokenUseCase;

    public static Controller createAuthController() {
        if (authController == null) {
            authController = new AuthController(createAuthHandler());
        }
        return authController;
    }

    public static AuthHandler createAuthHandler() {
        if (authHandler == null) {
            authHandler = new AuthHandlerImpl(createLoginUseCase(), createStoreTokenUseCase());
        }
        return authHandler;
    }

    public static LoginUseCase createLoginUseCase() {
        if (loginUseCase == null) {
            loginUseCase = new LoginService(UserFactory.createUserRepository());
        }
        return loginUseCase;
    }

    public static StoreTokenUseCase createStoreTokenUseCase() {
        if (storeTokenUseCase == null) {
            storeTokenUseCase = new StoreTokenService(UserFactory.createUserRepository());
        }
        return storeTokenUseCase;
    }
}
