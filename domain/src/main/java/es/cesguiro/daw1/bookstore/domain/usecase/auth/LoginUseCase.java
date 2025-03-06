package es.cesguiro.daw1.bookstore.domain.usecase.auth;

import es.cesguiro.daw1.bookstore.domain.model.User;

import java.util.Optional;

public interface LoginUseCase {

    Optional<User> login(String email, String password);
}
