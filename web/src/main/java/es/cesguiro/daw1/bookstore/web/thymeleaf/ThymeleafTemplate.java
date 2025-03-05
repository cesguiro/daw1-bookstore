package es.cesguiro.daw1.bookstore.web.thymeleaf;

import es.cesguiro.daw1.bookstore.util.context.RequestContextHolder;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.templateresolver.WebApplicationTemplateResolver;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;
import java.util.Locale;

public class ThymeleafTemplate implements Template{

    private WebContext context;
    private TemplateEngine templateEngine;
    private HttpServletResponse response;

    @Override
    public void init(HttpServletRequest request, HttpServletResponse response) {
        this.response = response;
        ServletContext servletContext = request.getServletContext();
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");

        JakartaServletWebApplication application = JakartaServletWebApplication.buildApplication(servletContext);
        IWebExchange webExchange = application.buildExchange(request, response);

        context = new WebContext(webExchange);

        Locale locale = RequestContextHolder.getRequestContext().getLocale();

        context.setLocale(locale);
        context.setVariable("currentLocale", locale.getLanguage());

        setTemplateEngine(application);
    }

    private void setTemplateEngine(JakartaServletWebApplication application) {
        WebApplicationTemplateResolver templateResolver = new WebApplicationTemplateResolver(application);

        templateResolver.setTemplateMode("HTML");
        templateResolver.setPrefix("templates/");
        templateResolver.setSuffix(".html");
        templateResolver.setCharacterEncoding("UTF-8");

        templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);

        templateEngine.setMessageResolver(new CustomMessageResolver());

    }


    public TemplateEngine getTemplateEngine() {
        return templateEngine;
    }

    @Override
    public void setVariable(String name, Object value) {
        context.setVariable(name, value);
    }

     @Override
    public void process(String template) {
        try {
            templateEngine.process(template, context, this.response.getWriter());
        } catch (IOException e) {
            throw new RuntimeException("Error processing template", e);
        }
    }
}
