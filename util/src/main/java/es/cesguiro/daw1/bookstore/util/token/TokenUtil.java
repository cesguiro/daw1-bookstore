package es.cesguiro.daw1.bookstore.util.token;

public class TokenUtil {

    private static TokenProvider tokenProvider;

    public static TokenProvider getTokenProvider() {
        if (tokenProvider == null) {
            tokenProvider = new CustomTokenProvider();
        }
        return tokenProvider;
    }
}
