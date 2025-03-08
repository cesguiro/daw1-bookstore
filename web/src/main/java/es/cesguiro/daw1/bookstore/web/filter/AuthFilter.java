package es.cesguiro.daw1.bookstore.web.filter;

import es.cesguiro.daw1.bookstore.util.context.RequestContextHolder;
import es.cesguiro.daw1.bookstore.web.controller.Controller;
import es.cesguiro.daw1.bookstore.web.controller.UserController;
import es.cesguiro.daw1.bookstore.web.factory.UserFactory;
import jakarta.servlet.*;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class AuthFilter implements Filter {

    private Controller userController;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        userController = UserFactory.createUserController();
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        Cookie[] cookies = httpRequest.getCookies();
        String authToken = null;

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("auth_token")) {
                    authToken = cookie.getValue();
                    break;
                }
            }
        }
        if (authToken != null && !authToken.isEmpty()) {
            ((UserController) userController).findByToken(authToken).ifPresent(user -> RequestContextHolder.getRequestContext().setUser(user));
        }
        chain.doFilter(request, response);
    }
}
