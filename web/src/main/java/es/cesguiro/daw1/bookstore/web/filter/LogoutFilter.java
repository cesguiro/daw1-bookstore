package es.cesguiro.daw1.bookstore.web.filter;

import es.cesguiro.daw1.bookstore.web.util.CookieUtil;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class LogoutFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpServletRequest httpRequest = (HttpServletRequest) request;

        // Remove the cookie
        CookieUtil.deleteCookie(httpResponse, "auth_token");
        httpResponse.sendRedirect("/");

    }
}
