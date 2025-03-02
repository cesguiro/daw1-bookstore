package es.cesguiro.daw1.bookstore.web.router;

import jakarta.servlet.http.HttpServletRequest;

import java.util.*;

public class Routes {

    private final List<Route> routes = new ArrayList<>();

    public void add(Route route){
        routes.add(route);
    }

    public Optional<Route> getRoute(HttpServletRequest request){
        String method = request.getMethod();
        String path = request.getRequestURI();
        return routes.stream()
                .filter(route -> route.matches(method, path))
                .findFirst();
    }
}
