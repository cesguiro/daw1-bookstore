package es.cesguiro.daw1.bookstore.application.handler;

import es.cesguiro.daw1.bookstore.domain.model.User;

import java.util.Optional;

public interface UserHandler {

    User insert(User user);

    Optional<User> findByToken(String authToken);
}
