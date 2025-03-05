package es.cesguiro.daw1.bookstore.application.handler;

import es.cesguiro.daw1.bookstore.domain.model.User;

public interface AuthHandler {

    User register(User user);
}
