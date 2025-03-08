package es.cesguiro.daw1.bookstore.web.filter;

import es.cesguiro.daw1.bookstore.domain.model.User;
import es.cesguiro.daw1.bookstore.util.context.RequestContextHolder;
import es.cesguiro.daw1.bookstore.util.token.TokenUtil;
import es.cesguiro.daw1.bookstore.web.controller.AuthController;
import es.cesguiro.daw1.bookstore.web.controller.Controller;
import es.cesguiro.daw1.bookstore.web.factory.AuthFactory;
import es.cesguiro.daw1.bookstore.web.factory.TemplateFactory;
import es.cesguiro.daw1.bookstore.web.thymeleaf.Template;
import es.cesguiro.daw1.bookstore.web.util.CookieUtil;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Optional;

public class LoginFilter implements Filter {

    private Controller authController;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.authController = AuthFactory.createAuthController();
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        if (RequestContextHolder.getRequestContext().getUser() != null) {
            httpResponse.sendRedirect("/");
        }
        String method = httpRequest.getMethod().toUpperCase();

        if (method.equals("POST")) {
            String email = httpRequest.getParameter("email");
            String password = httpRequest.getParameter("password");
            HttpSession session = httpRequest.getSession(true);

            Optional<User> userOptional = ((AuthController) authController).login(email, password);
            if (userOptional.isPresent()) {
                User user = userOptional.get();
                String token = TokenUtil.getTokenProvider().generateToken();
                try {
                    ((AuthController) authController).storeToken(user, token);
                    //session.setAttribute("userId", user.getId());
                    CookieUtil.setCookie(httpResponse, "auth_token", token, 3600);
                    httpResponse.sendRedirect("/");
                } catch (Exception e) {
                    throw new RuntimeException("Error building token", e);
                }
            } else {
                /*session.setAttribute("error", "error.login");
                httpResponse.sendRedirect("/login");*/
                Template template = TemplateFactory.createTemplate();
                template.init(httpRequest, httpResponse);
                template.setVariable("error", "error.login");
                template.setVariable("requestContext", RequestContextHolder.getRequestContext());
                ((AuthController) authController).showLoginForm(httpRequest, httpResponse);
            }
        } else if (method.equals("GET")) {
            Template template = TemplateFactory.createTemplate();
            template.init(httpRequest, httpResponse);
            template.setVariable("requestContext", RequestContextHolder.getRequestContext());
            ((AuthController) authController).showLoginForm(httpRequest, httpResponse);
        } else {
            chain.doFilter(request, response);
        }
    }
}
