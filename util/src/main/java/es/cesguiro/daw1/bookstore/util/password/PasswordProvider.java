package es.cesguiro.daw1.bookstore.util.password;

public interface PasswordProvider {

    String hashPassword(String password, String salt);
    boolean checkPassword(String password, String hashedPassword);
    String generateSalt();
}
