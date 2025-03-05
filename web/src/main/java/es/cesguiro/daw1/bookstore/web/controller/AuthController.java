package es.cesguiro.daw1.bookstore.web.controller;

import es.cesguiro.daw1.bookstore.application.handler.AuthHandler;
import es.cesguiro.daw1.bookstore.domain.model.User;
import es.cesguiro.daw1.bookstore.web.factory.TemplateFactory;
import es.cesguiro.daw1.bookstore.web.router.Method;
import es.cesguiro.daw1.bookstore.web.router.Route;
import es.cesguiro.daw1.bookstore.web.router.Routes;
import es.cesguiro.daw1.bookstore.web.thymeleaf.Template;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class AuthController implements Controller {

    private final Template template = TemplateFactory.getTemplate();
    private final AuthHandler authHandler;

    public AuthController(AuthHandler authHandler) {
        this.authHandler = authHandler;
    }

    @Override
    public void registerRoutes(Routes routes) {
        routes.add(new Route(Method.GET, "/login", this::showLoginForm));
        routes.add(new Route(Method.POST, "/login", this::login));
        routes.add(new Route(Method.GET, "/logout", this::logout));
        routes.add(new Route(Method.GET, "/register", this::showRegisterForm));
        routes.add(new Route(Method.POST, "/users", this::register));
    }


    public void showLoginForm(HttpServletRequest request, HttpServletResponse response) {
        template.process("auth/login");
    }

    public void login(HttpServletRequest request, HttpServletResponse response) {
        // Login user
    }

    public void logout(HttpServletRequest request, HttpServletResponse response) {
        // Logout user
    }

    public void showRegisterForm(HttpServletRequest request, HttpServletResponse response) {
        template.process("auth/register");
    }

    public void register(HttpServletRequest request, HttpServletResponse response) {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        String address = request.getParameter("address");
        String language = request.getParameter("language").toLowerCase();
        User user = new User(null, email, password, name, address, language, false);
        authHandler.register(user);
        try {
            response.sendRedirect("/");
        } catch (IOException e) {
            throw new RuntimeException("Error redirecting to home", e);
        }
    }

}
