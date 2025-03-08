package es.cesguiro.daw1.bookstore.web.controller;

import es.cesguiro.daw1.bookstore.web.factory.TemplateFactory;
import es.cesguiro.daw1.bookstore.web.router.Method;
import es.cesguiro.daw1.bookstore.web.router.Route;
import es.cesguiro.daw1.bookstore.web.router.Routes;
import es.cesguiro.daw1.bookstore.web.thymeleaf.Template;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class MainController implements Controller {

    private final Template template = TemplateFactory.createTemplate();

    @Override
    public void registerRoutes(Routes routes) {
        routes.add(new Route(Method.GET, "/", this::index));
        routes.add(new Route(Method.GET, "/error", this::error));
    }

    public void index(HttpServletRequest request, HttpServletResponse response) {
        template.process("index");
    }

    public void error(HttpServletRequest request, HttpServletResponse response) {
        //Integer statusCode = (Integer) request.getAttribute("statusCode");
        Integer statusCode = (Integer) request.getSession().getAttribute("statusCode");
        if (statusCode == null) {
            statusCode = 500;
        }

        request.getSession().removeAttribute("statusCode");

        template.setVariable("statusCode", statusCode);
        template.process("error");
    }

}
