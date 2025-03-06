package es.cesguiro.daw1.bookstore.domain.usecase.user;

import es.cesguiro.daw1.bookstore.domain.model.User;

import java.util.Optional;

public interface FindUserByCriteriaUseCase {


    Optional<User> findByToken(String authToken);
}
