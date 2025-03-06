package es.cesguiro.daw1.bookstore.domain.usecase.auth;

import es.cesguiro.daw1.bookstore.domain.model.User;

public interface StoreTokenUseCase {

    void storeToken(User user, String token);
}
