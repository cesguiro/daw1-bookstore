package es.cesguiro.daw1.bookstore.web.router;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Route {

    private final Method method;
    private final String pathTemplate;
    private final String[] paramNames;
    private final Pattern pattern;
    private final BiConsumer<HttpServletRequest, HttpServletResponse> handler;

    public Route(Method method, String pathTemplate, BiConsumer<HttpServletRequest, HttpServletResponse> handler) {
        this.method = method;
        this.pathTemplate = pathTemplate;
        this.handler = handler;
        this.paramNames = extractParamNames();
        this.pattern = Pattern.compile(convertPathToRegex(pathTemplate));
    }

    public BiConsumer<HttpServletRequest, HttpServletResponse> getHandler() {
        return handler;
    }

    public boolean matches(String method, String path) {
        return this.method.name().equals(method.toUpperCase()) && pattern.matcher(path).matches();
    }

    private static String convertPathToRegex(String pathTemplate) {
        return "^" + pathTemplate.replaceAll("\\{[^/]+}", "([^/]+)") + "$";
    }

    public String getMethod() {
        return method.name();
    }

    public Map<String, String> getParams(String path){
        Matcher matcher = pattern.matcher(path);
        if (!matcher.matches()) {
            return Collections.emptyMap();
        }
        Map<String, String> params = new HashMap<>();
        for (int i = 0; i < paramNames.length; i++) {
            params.put(paramNames[i], matcher.group(i + 1));
        }
        return Collections.unmodifiableMap(params);
    }

    private String[] extractParamNames() {
        List<String> paramNamesList = new ArrayList<>();
        Matcher paramMatcher = Pattern.compile("\\{(\\w+)}").matcher(pathTemplate);

        while (paramMatcher.find()) {
            paramNamesList.add(paramMatcher.group(1));
        }

        return paramNamesList.isEmpty() ? new String[0] : paramNamesList.toArray(new String[0]);
    }
}
