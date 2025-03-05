package es.cesguiro.daw1.bookstore.util.password;

public class PasswordUtil {

    private static PasswordProvider passwordProvider;

    public static PasswordProvider getPasswordProvider() {
        if (passwordProvider == null) {
            passwordProvider = new CustomPasswordProvider();
        }
        return passwordProvider;
    }
}
