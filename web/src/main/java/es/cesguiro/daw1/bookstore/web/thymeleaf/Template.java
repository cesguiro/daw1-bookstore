package es.cesguiro.daw1.bookstore.web.thymeleaf;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface Template {

    public void init(HttpServletRequest request, HttpServletResponse response, ServletContext servletContext);
    public void setVariable(String name, Object value);
    public void process(String template);
}
