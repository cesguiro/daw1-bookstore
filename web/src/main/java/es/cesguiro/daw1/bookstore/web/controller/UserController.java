package es.cesguiro.daw1.bookstore.web.controller;

import es.cesguiro.daw1.bookstore.application.handler.AuthHandler;
import es.cesguiro.daw1.bookstore.application.handler.UserHandler;
import es.cesguiro.daw1.bookstore.domain.exception.ValidationException;
import es.cesguiro.daw1.bookstore.domain.model.User;
import es.cesguiro.daw1.bookstore.util.token.TokenUtil;
import es.cesguiro.daw1.bookstore.web.factory.TemplateFactory;
import es.cesguiro.daw1.bookstore.web.router.Method;
import es.cesguiro.daw1.bookstore.web.router.Route;
import es.cesguiro.daw1.bookstore.web.router.Routes;
import es.cesguiro.daw1.bookstore.web.thymeleaf.Template;
import es.cesguiro.daw1.bookstore.web.util.CookieUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Optional;

public class UserController implements Controller {

    private final UserHandler userHandler;
    private final AuthHandler authHandler;
    private final Template template = TemplateFactory.createTemplate();

    public UserController(UserHandler userHandler, AuthHandler authHandler) {
        this.userHandler = userHandler;
        this.authHandler = authHandler;
    }

    @Override
    public void registerRoutes(Routes routes) {
        routes.add(new Route(Method.POST, "/register", this::insert));
    }

    public void insert(HttpServletRequest request, HttpServletResponse response) {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        String address = request.getParameter("address");
        String language = request.getParameter("language").toLowerCase();
        User newUser = new User(null, email, password, name, address, language, false);
        try {
            User user= userHandler.insert(newUser);
            String token = TokenUtil.getTokenProvider().generateToken();
            authHandler.storeToken(user, token);
            CookieUtil.setCookie(response, "auth_token", token, 3600);
            response.sendRedirect("/");
        } catch (ValidationException e) {
            HttpSession session = request.getSession();
            request.setAttribute("errors", e.getErrors());
            request.setAttribute("user", newUser);
            template.setVariable("user", newUser);
            template.setVariable("errors", e.getErrors());
            template.process("auth/register");

        } catch (IOException e) {
            throw new RuntimeException("Error redirecting to home", e);
        }
    }

    public Optional<User> findByToken(String authToken) {
        return userHandler.findByToken(authToken);
    }
}
