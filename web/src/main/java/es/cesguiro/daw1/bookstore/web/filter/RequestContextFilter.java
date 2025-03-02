package es.cesguiro.daw1.bookstore.web.filter;


import es.cesguiro.daw1.bookstore.util.context.RequestContext;
import es.cesguiro.daw1.bookstore.util.context.RequestContextHolder;
import es.cesguiro.daw1.bookstore.util.exception.CustomException;
import es.cesguiro.daw1.bookstore.util.exception.Error500;
import es.cesguiro.daw1.bookstore.util.property.PropertyUtil;
import es.cesguiro.daw1.bookstore.web.TomcatServer;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Locale;

/*@WebFilter("/*")
@Priority(10)*/
/*@WebFilter("/*")
@Priority(10)*/
public class RequestContextFilter implements Filter {

    private static final Logger logger = LogManager.getLogger(RequestContextFilter.class);

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        RequestContext requestContext = new RequestContext();
        RequestContextHolder.setRequestContext(requestContext);

        try {
            setupDatabaseConnection();
            chain.doFilter(request, response);
        } catch (CustomException e) {
            handleError(httpRequest, httpResponse, e.getStatusCode(), e.getMessage(), e);
        } catch (Exception e) {
            logger.error("Unexpected error", e);
            handleError(httpRequest, httpResponse, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Internal error", e);
        } finally {
            RequestContextHolder.clear();
        }
    }

    private void setupDatabaseConnection() throws NamingException, SQLException {
        InitialContext initialContext = new InitialContext();
        DataSource dataSource = (DataSource) initialContext.lookup("java:comp/env/" + PropertyUtil.getPropertyProvider().getProperty("app.datasource.jndi"));
        RequestContextHolder.getRequestContext().setConnection(dataSource.getConnection());
    }

    private void handleError(HttpServletRequest httpRequest, HttpServletResponse httpResponse, int statusCode, String errorMessage, Exception e) {
        logger.error("Error in RequestContextFilter", e);
        RequestContextHolder.getRequestContext().setLocale(Locale.of("es"));
        try {
            httpRequest.setAttribute("statusCode", statusCode);
            httpRequest.setAttribute("errorMessage", errorMessage);
            httpRequest.getRequestDispatcher("/error").forward(httpRequest, httpResponse);
        } catch (ServletException | IOException ex) {
            throw new RuntimeException("Error while forwarding to error page", ex);
        }
    }
}
