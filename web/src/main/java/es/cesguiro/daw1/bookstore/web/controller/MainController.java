package es.cesguiro.daw1.bookstore.web.controller;

import es.cesguiro.daw1.bookstore.web.thymeleaf.Template;
import es.cesguiro.daw1.bookstore.web.thymeleaf.TemplateThymeleaf;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class MainController {

    Template template = new TemplateThymeleaf();

    public void index(HttpServletRequest request, HttpServletResponse response) {
        template.init(request, response, request.getServletContext());
        template.process("index");
    }

    public void error(HttpServletRequest request, HttpServletResponse response) {
        Integer statusCode = (Integer) request.getAttribute("statusCode");
        String errorMessage = (String) request.getAttribute("errorMessage");
        template.init(request, response, request.getServletContext());
        template.setVariable("statusCode", statusCode);
        template.setVariable("errorMessage", errorMessage);
        template.process("error");
    }

}
