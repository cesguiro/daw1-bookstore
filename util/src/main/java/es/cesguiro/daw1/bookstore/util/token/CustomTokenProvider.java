package es.cesguiro.daw1.bookstore.util.token;

import java.security.SecureRandom;
import java.util.Base64;

public class CustomTokenProvider implements TokenProvider {

    private final SecureRandom secureRandom = new SecureRandom();
    private final int TOKEN_LENGTH = 64;

    @Override
    public String generateToken() {
        byte[] bytes = new byte[TOKEN_LENGTH];
        secureRandom.nextBytes(bytes);
        return Base64.getUrlEncoder().withoutPadding().encodeToString(bytes);
    }
}
