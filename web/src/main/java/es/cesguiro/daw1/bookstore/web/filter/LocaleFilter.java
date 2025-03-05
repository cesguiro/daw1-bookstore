package es.cesguiro.daw1.bookstore.web.filter;

import es.cesguiro.daw1.bookstore.util.context.RequestContextHolder;
import es.cesguiro.daw1.bookstore.util.language.LanguageUtil;
import es.cesguiro.daw1.bookstore.util.property.PropertyUtil;
import es.cesguiro.daw1.bookstore.web.util.CookieUtil;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.Locale;

// No se puede especificar el orden de los filtros si se utilizan anotaciones
/*@WebFilter("/*")*/
public class LocaleFilter implements Filter {

    private static final String COOKIE_NAME = "frontend_lang";
    private static final String DEFAULT_LANGUAGE = "es";

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        String defaultLanguage = PropertyUtil.getPropertyProvider().getProperty("app.language.default", DEFAULT_LANGUAGE);
        String language = CookieUtil.getCookieValue(httpRequest, COOKIE_NAME, defaultLanguage);
        String lang = LanguageUtil.getAllowLanguage(language);

        CookieUtil.setCookie(httpResponse, COOKIE_NAME, lang, 60 * 60 * 24 * 30);

        RequestContextHolder.getRequestContext().setLocale(Locale.of(language));

        chain.doFilter(request, response);
    }

}
