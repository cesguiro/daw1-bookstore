package es.cesguiro.daw1.bookstore.domain.service.user;

import es.cesguiro.daw1.bookstore.domain.model.User;
import es.cesguiro.daw1.bookstore.domain.repository.UserRepository;
import es.cesguiro.daw1.bookstore.domain.usecase.user.FindUserByCriteriaUseCase;

import java.util.Optional;

public class FindUserByCriteriaService implements FindUserByCriteriaUseCase {

    private final UserRepository userRepository;

    public FindUserByCriteriaService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<User> findByToken(String authToken) {
        return userRepository.findByToken(authToken);
    }
}
