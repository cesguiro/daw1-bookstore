package es.cesguiro.daw1.bookstore.domain.service.auth;

import es.cesguiro.daw1.bookstore.domain.model.User;
import es.cesguiro.daw1.bookstore.domain.repository.UserRepository;
import es.cesguiro.daw1.bookstore.domain.usecase.auth.StoreTokenUseCase;

public class StoreTokenService implements StoreTokenUseCase {

    private final UserRepository userRepository;

    public StoreTokenService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void storeToken(User user, String token) {
        userRepository.storeToken(user, token);
    }
}
