package es.cesguiro.daw1.bookstore.web.controller;

import es.cesguiro.daw1.bookstore.application.handler.AuthHandler;
import es.cesguiro.daw1.bookstore.domain.model.User;
import es.cesguiro.daw1.bookstore.util.context.RequestContextHolder;
import es.cesguiro.daw1.bookstore.web.factory.TemplateFactory;
import es.cesguiro.daw1.bookstore.web.router.Method;
import es.cesguiro.daw1.bookstore.web.router.Route;
import es.cesguiro.daw1.bookstore.web.router.Routes;
import es.cesguiro.daw1.bookstore.web.thymeleaf.Template;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Optional;

public class AuthController implements Controller {

    private final Template template = TemplateFactory.getTemplate();
    private final AuthHandler authHandler;

    public AuthController(AuthHandler authHandler) {
        this.authHandler = authHandler;
    }

    @Override
    public void registerRoutes(Routes routes) {
        routes.add(new Route(Method.GET, "/register", this::showRegisterForm));
    }


    public void showLoginForm(HttpServletRequest request, HttpServletResponse response) {
        template.process("auth/login");
    }

    public Optional<User> login(String email, String password) {
        return authHandler.login(email, password);
    }

    public void storeToken(User user, String token) {
        authHandler.storeToken(user, token);
    }

    public void showRegisterForm(HttpServletRequest request, HttpServletResponse response) {
        if (RequestContextHolder.getRequestContext().getUser() != null) {
            try {
                response.sendRedirect("/");
            } catch (IOException e) {
                throw new RuntimeException("Error redirecting to home", e);
            }
        }
        template.process("auth/register");
    }

}
