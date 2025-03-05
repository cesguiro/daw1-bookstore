package es.cesguiro.daw1.bookstore.domain.service.user;

import es.cesguiro.daw1.bookstore.domain.exception.BusinessException;
import es.cesguiro.daw1.bookstore.domain.model.User;
import es.cesguiro.daw1.bookstore.domain.repository.UserRepository;
import es.cesguiro.daw1.bookstore.domain.usecase.user.InsertUserUseCase;
import es.cesguiro.daw1.bookstore.util.language.LanguageUtil;
import es.cesguiro.daw1.bookstore.util.password.PasswordUtil;

public class InsertUserService implements InsertUserUseCase {

    private final UserRepository userRepository;

    public InsertUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User insertUser(User user) {
        userRepository.getByEmail(user.getEmail()).ifPresent(u -> {
            throw new BusinessException("User already exists");
        });

        String salt = PasswordUtil.getPasswordProvider().generateSalt();
        String password = PasswordUtil.getPasswordProvider().hashPassword(user.getPassword(), salt);
        user.setPassword(password);

        String lang = LanguageUtil.getAllowLanguage(user.getLanguage().toLowerCase());
        user.setLanguage(lang);

        return userRepository.save(user);
    }
}
