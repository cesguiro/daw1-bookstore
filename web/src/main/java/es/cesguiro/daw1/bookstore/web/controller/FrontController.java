package es.cesguiro.daw1.bookstore.web.controller;

import es.cesguiro.daw1.bookstore.util.exception.Error404;
import es.cesguiro.daw1.bookstore.web.router.Method;
import es.cesguiro.daw1.bookstore.web.router.Route;
import es.cesguiro.daw1.bookstore.web.router.Routes;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.Map;

//@WebServlet("/*")
public class FrontController extends HttpServlet {
    private final BookController bookController = new BookController();
    private final MainController mainController = new MainController();
    private final LocaleController localeController = new LocaleController();
    private final Routes routes = new Routes();

    @Override
    public void init() {
        routes.add(new Route(Method.GET,"/", mainController::index));
        routes.add(new Route(Method.GET,"/error", mainController::error));
        routes.add(new Route(Method.GET,"/books", bookController::findAll));
        routes.add(new Route(Method.GET,"/books/{isbn}", bookController::findByIsbn));
        routes.add(new Route(Method.GET,"/locale/{lang}", localeController::changeLanguage));
    }

    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) {
        Route route = routes.getRoute(request).orElse(null);
        if (route == null) {
            throw new Error404("Resource not found");
        }
        Map<String, String> params = route.getParams(request.getRequestURI());
        for (Map.Entry<String, String> entry : params.entrySet()) {
            request.setAttribute(entry.getKey(), entry.getValue());
        }
        route.getHandler().accept(request, response);
    }


}
