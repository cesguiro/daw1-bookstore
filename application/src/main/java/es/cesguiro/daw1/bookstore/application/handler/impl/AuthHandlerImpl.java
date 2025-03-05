package es.cesguiro.daw1.bookstore.application.handler.impl;


import es.cesguiro.daw1.bookstore.application.handler.AuthHandler;
import es.cesguiro.daw1.bookstore.domain.model.User;
import es.cesguiro.daw1.bookstore.domain.usecase.user.InsertUserUseCase;

public class AuthHandlerImpl implements AuthHandler {

    private final InsertUserUseCase insertUserUseCase;

    public AuthHandlerImpl(InsertUserUseCase insertUserUseCase) {
        this.insertUserUseCase = insertUserUseCase;
    }

    @Override
    public User register(User user) {
        return insertUserUseCase.insertUser(user);
    }

}
