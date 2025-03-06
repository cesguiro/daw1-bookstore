package es.cesguiro.daw1.bookstore.application.handler.impl;

import es.cesguiro.daw1.bookstore.application.handler.UserHandler;
import es.cesguiro.daw1.bookstore.domain.model.User;
import es.cesguiro.daw1.bookstore.domain.usecase.user.FindUserByCriteriaUseCase;
import es.cesguiro.daw1.bookstore.domain.usecase.user.InsertUserUseCase;

import java.util.Optional;

public class UserHandlerImpl implements UserHandler {

    private final InsertUserUseCase insertUserUseCase;
    private final FindUserByCriteriaUseCase findUserByCriteriaUseCase;

    public UserHandlerImpl(InsertUserUseCase insertUserUseCase, FindUserByCriteriaUseCase findUserByCriteriaUseCase) {
        this.insertUserUseCase = insertUserUseCase;
        this.findUserByCriteriaUseCase = findUserByCriteriaUseCase;
    }

    @Override
    public User insert(User user) {
        return insertUserUseCase.insertUser(user);
    }

    @Override
    public Optional<User> findByToken(String authToken) {
        return findUserByCriteriaUseCase.findByToken(authToken);
    }

}
