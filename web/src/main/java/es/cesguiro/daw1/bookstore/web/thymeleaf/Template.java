package es.cesguiro.daw1.bookstore.web.thymeleaf;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface Template {

    void init(HttpServletRequest request, HttpServletResponse response);
    void setVariable(String name, Object value);
    void process(String template);
}
