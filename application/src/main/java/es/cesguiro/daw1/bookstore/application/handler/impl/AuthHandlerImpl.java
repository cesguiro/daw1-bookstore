package es.cesguiro.daw1.bookstore.application.handler.impl;


import es.cesguiro.daw1.bookstore.application.handler.AuthHandler;
import es.cesguiro.daw1.bookstore.domain.model.User;
import es.cesguiro.daw1.bookstore.domain.usecase.auth.LoginUseCase;
import es.cesguiro.daw1.bookstore.domain.usecase.auth.StoreTokenUseCase;

import java.util.Optional;

public class AuthHandlerImpl implements AuthHandler {

    private final LoginUseCase loginUseCase;
    private final StoreTokenUseCase storeTokenUseCase;

    public AuthHandlerImpl(LoginUseCase loginUseCase, StoreTokenUseCase storeTokenUseCase) {
        this.loginUseCase = loginUseCase;
        this.storeTokenUseCase = storeTokenUseCase;
    }

    @Override
    public Optional<User> login(String email, String password) {
        return loginUseCase.login(email, password);
    }

    @Override
    public void storeToken(User user, String token) {
        storeTokenUseCase.storeToken(user, token);
    }


}
