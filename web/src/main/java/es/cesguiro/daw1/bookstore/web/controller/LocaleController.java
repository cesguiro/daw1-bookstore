package es.cesguiro.daw1.bookstore.web.controller;

import es.cesguiro.daw1.bookstore.util.property.PropertyUtil;
import es.cesguiro.daw1.bookstore.web.util.CookieUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.List;

public class LocaleController {

    private final String DEFAULT_LANGUAGE = "es";
    private final String COOKIE_NAME = "frontend_lang";
    private final String SUPPORTED_LANGUAGES_PROPERTY = "app.language.supported";

    public void changeLanguage(HttpServletRequest request, HttpServletResponse response) {
        Logger logger = LogManager.getLogger(LocaleController.class);
        String lang = (String) request.getAttribute("lang");
        String redirect = request.getParameter("redirect");
        if (redirect == null) {
            redirect = "/";
        }
        String supportedLanguagesConfig = PropertyUtil.getPropertyProvider().getProperty(SUPPORTED_LANGUAGES_PROPERTY, DEFAULT_LANGUAGE);
        List<String> supportedLanguages = Arrays.asList(supportedLanguagesConfig.split(","));
        if (supportedLanguages.contains(lang)) {
            CookieUtil.setCookie(response, COOKIE_NAME, lang, 60 * 60 * 24 * 30);
        }
        try {
            response.sendRedirect(redirect);
        } catch (Exception e) {
            logger.error("Error redirecting to {}", redirect, e);
        }
    }
}
