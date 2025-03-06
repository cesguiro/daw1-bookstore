package es.cesguiro.daw1.bookstore.util.password;

import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Base64;

public class CustomPasswordProvider implements PasswordProvider{

    private final SecureRandom random = new SecureRandom();
    private final int SALT_LENGTH = 16;
    private final int ITERATIONS = 1000;
    private final String HASH_ALGORITHM = "SHA-256";

    @Override
    public String hashPassword(String password, String salt) {
        //Almacena la contraseña hasheada y el salt en un solo string
        String saltedPassword = password + salt;
        try {
            // Se crea un objeto MessageDigest con el algoritmo de hash
            MessageDigest messageDigest = MessageDigest.getInstance(HASH_ALGORITHM);
            // Se aplica el hash a la contraseña y el salt
            byte[] hash = saltedPassword.getBytes();
            // Se aplica varias veces el hash a la contraseña y el salt
            for (int i = 0; i < ITERATIONS; i++) {
                hash = messageDigest.digest(hash);
            }

            // Se convierte el hash en un string hexadecimal
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                hexString.append(String.format("%02x", b));
            }

            // Se devuelve el hash y el salt en un solo string
            return hexString + ":" + salt;
        } catch (Exception e) {
            throw new RuntimeException("Error hashing password", e);
        }
    }

    public String generateSalt() {
        byte[] salt = new byte[SALT_LENGTH];
        random.nextBytes(salt);
        return Base64.getEncoder().encodeToString(salt);
    }

    @Override
    public boolean checkPassword(String password, String storedHash) {
        String[] parts = storedHash.split(":");
        if (parts.length != 2) {
            return false;
        }
        String salt = parts[1];
        String computedHash = hashPassword(password, salt);
        return computedHash.equals(storedHash);
    }

    @Override
    public boolean isPasswordValid(String password) {
        return password.length() >= 6;
    }

}
