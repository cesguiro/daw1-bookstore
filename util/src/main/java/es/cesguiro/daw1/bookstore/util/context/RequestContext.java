package es.cesguiro.daw1.bookstore.util.context;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.util.Locale;

public class RequestContext {

    private Locale locale;
    private Connection connection;
    UserContext user;

    public RequestContext() {
    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public void setUser(UserContext user) {
        this.user = user;
    }

    public UserContext getUser() {
        return user;
    }
}
