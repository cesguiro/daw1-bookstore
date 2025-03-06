package es.cesguiro.daw1.bookstore.domain.service.auth;

import es.cesguiro.daw1.bookstore.domain.model.User;
import es.cesguiro.daw1.bookstore.domain.repository.UserRepository;
import es.cesguiro.daw1.bookstore.domain.usecase.auth.LoginUseCase;
import es.cesguiro.daw1.bookstore.util.password.PasswordUtil;

import java.util.Optional;

public class LoginService implements LoginUseCase {

    private final UserRepository userRepository;

    public LoginService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<User> login(String email, String password) {
        return userRepository.findByEmail(email)
                .filter(user -> PasswordUtil.getPasswordProvider().checkPassword(password, user.getPassword()));
    }
}
