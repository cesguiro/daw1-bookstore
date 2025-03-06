package es.cesguiro.daw1.bookstore.web.filter;


import es.cesguiro.daw1.bookstore.domain.exception.BusinessException;
import es.cesguiro.daw1.bookstore.domain.exception.ValidationException;
import es.cesguiro.daw1.bookstore.util.context.RequestContext;
import es.cesguiro.daw1.bookstore.util.context.RequestContextHolder;
import es.cesguiro.daw1.bookstore.util.property.PropertyUtil;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.io.IOException;
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
        RequestContextHolder.getRequestContext().setUser(null);

        try {
            setupDatabaseConnection();
            chain.doFilter(request, response);
        } catch (BusinessException e) {
            logger.error("Business error", e);
            handleError(httpRequest, httpResponse, 404, e);
        } catch (ValidationException e) {
            logger.error("Validation error", e);
            handleError(httpRequest, httpResponse, 400, e);
        } catch (RuntimeException e) {
            logger.error("Runtime error", e);
            handleError(httpRequest, httpResponse, 500, e);
        } catch (Exception e) {
            logger.error("Unexpected error", e);
            handleError(httpRequest, httpResponse, 500, e);
        } finally {
            RequestContextHolder.clear();
        }
    }

    private void setupDatabaseConnection() throws NamingException, SQLException {
        InitialContext initialContext = new InitialContext();
        DataSource dataSource = (DataSource) initialContext.lookup("java:comp/env/" + PropertyUtil.getPropertyProvider().getProperty("app.datasource.jndi"));
        RequestContextHolder.getRequestContext().setConnection(dataSource.getConnection());
    }

    private void handleError(HttpServletRequest httpRequest, HttpServletResponse httpResponse, int statusCode, Exception e) {
        if (RequestContextHolder.getRequestContext().getLocale() == null) {
            RequestContextHolder.getRequestContext().setLocale(Locale.of("es"));
        }
        try {
            // getRequestDispatcher para poder pasar los atributos de la request y response
            /*httpRequest.setAttribute("statusCode", statusCode);
            httpRequest.getRequestDispatcher("/error").forward(httpRequest, httpResponse);*/

            // Usamos getSession para poder pasar los atributos de la request y response
            httpRequest.getSession(true).setAttribute("statusCode", statusCode);
            httpResponse.sendRedirect("/error");
        } catch (Exception ex) {
            throw new RuntimeException("Error while forwarding to error page", ex);
        }
    }
}
