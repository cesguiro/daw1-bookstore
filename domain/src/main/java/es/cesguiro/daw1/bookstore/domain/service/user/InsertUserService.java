package es.cesguiro.daw1.bookstore.domain.service.user;

import es.cesguiro.daw1.bookstore.domain.exception.ValidationException;
import es.cesguiro.daw1.bookstore.domain.model.User;
import es.cesguiro.daw1.bookstore.domain.repository.UserRepository;
import es.cesguiro.daw1.bookstore.domain.usecase.user.InsertUserUseCase;
import es.cesguiro.daw1.bookstore.util.language.LanguageUtil;
import es.cesguiro.daw1.bookstore.util.password.PasswordUtil;

import java.util.HashMap;
import java.util.Map;


public class InsertUserService implements InsertUserUseCase {

    private final UserRepository userRepository;

    public InsertUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User insertUser(User user) {
        Map<String, String> errors = new HashMap<>();

        // Validación del correo electrónico
        validateEmail(user.getEmail(), errors);

        // Validación de la contraseña
        validatePassword(user.getPassword(), errors);

        // Verificar si ya existe un usuario con el mismo correo
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            errors.put("email", "error.email.exists");
        }

        // Si hay errores, lanzar una excepción con los detalles de los errores
        if (!errors.isEmpty()) {
            throw new ValidationException("Validation error", errors);
        }

        // Encriptar la contraseña antes de guardar al usuario
        String salt = PasswordUtil.getPasswordProvider().generateSalt();
        String password = PasswordUtil.getPasswordProvider().hashPassword(user.getPassword(), salt);
        user.setPassword(password);

        // Configuración del idioma
        String lang = LanguageUtil.getAllowLanguage(user.getLanguage().toLowerCase());
        user.setLanguage(lang);

        // Guardar el usuario en la base de datos
        return userRepository.save(user);
    }

    private void validateEmail(String email, Map<String, String> errors) {
        // Comprobar que el email no está vacío
        if (email == null || email.trim().isEmpty()) {
            errors.put("email", "error.required");
            return;
        }

        // Comprobar formato del email
        if (!isValidEmailFormat(email)) {
            errors.put("email", "error.email.invalid");
        }
    }

    private boolean isValidEmailFormat(String email) {
        // Utilizar una expresión regular para validar el formato del email
        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
        return email.matches(emailRegex);
    }

    private void validatePassword(String password, Map<String, String> errors) {
        // Comprobar que la contraseña no está vacía
        if (password == null || password.trim().isEmpty()) {
            errors.put("password", "error.required");
            return;
        }

        // Comprobar la validez de la contraseña (al menos 6 caracteres)
        if (!PasswordUtil.getPasswordProvider().isPasswordValid(password)) {
            errors.put("password", "error.password.invalid");
        }
    }
}
