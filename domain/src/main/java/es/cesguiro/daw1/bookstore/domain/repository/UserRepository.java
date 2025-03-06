package es.cesguiro.daw1.bookstore.domain.repository;

import es.cesguiro.daw1.bookstore.domain.model.User;

import java.util.Optional;

public interface UserRepository {

    Optional<User> findByEmail(String email);
    User save(User user);

    void storeToken(User user, String token);

    Optional<User> findByToken(String token);
}
