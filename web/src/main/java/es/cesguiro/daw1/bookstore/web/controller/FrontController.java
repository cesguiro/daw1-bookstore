package es.cesguiro.daw1.bookstore.web.controller;

import es.cesguiro.daw1.bookstore.domain.exception.BusinessException;
import es.cesguiro.daw1.bookstore.web.factory.BookFactory;
import es.cesguiro.daw1.bookstore.web.factory.TemplateFactory;
import es.cesguiro.daw1.bookstore.web.router.Method;
import es.cesguiro.daw1.bookstore.web.router.Route;
import es.cesguiro.daw1.bookstore.web.router.Routes;
import es.cesguiro.daw1.bookstore.web.thymeleaf.Template;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.util.List;
import java.util.Map;

//@WebServlet("/*")
public class FrontController extends HttpServlet {
    private final List<Controller> controllers;
    private final Routes routes = new Routes();
    private final Logger logger = LogManager.getLogger(FrontController.class);

    public FrontController(List<Controller> controllers) {
        this.controllers = controllers;
    }

    @Override
    public void init() {
        for (Controller controller : controllers) {
            controller.registerRoutes(routes);
        }
    }

    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) {
        // Crear la plantilla
        try {
            Template template = TemplateFactory.getTemplate();
            //JakartaServletWebApplication application = JakartaServletWebApplication.buildApplication(request.getServletContext());
            template.init(request, response);
        } catch (Exception e) {
            logger.error("Error creating template", e);
            throw new RuntimeException("Error creating template", e);
        }

        Route route = routes.getRoute(request).orElse(null);
        if (route == null) {
            throw new BusinessException("Route not found:" + request.getRequestURI());
        }
        Map<String, String> params = route.getParams(request.getRequestURI());
        for (Map.Entry<String, String> entry : params.entrySet()) {
            request.setAttribute(entry.getKey(), entry.getValue());
        }
        route.getHandler().accept(request, response);
    }


}
