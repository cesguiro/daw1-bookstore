package es.cesguiro.daw1.bookstore.util.context;

public class RequestContextHolder {

    private static final ThreadLocal<RequestContext> requestContext = new ThreadLocal<>();

    public static RequestContext getRequestContext() {
        return requestContext.get();
    }

    public static void setRequestContext(RequestContext ctx) {
        requestContext.set(ctx);
    }

    public static void clear() {
        RequestContext context = getRequestContext();
        try {
            if (context != null && context.getConnection() != null) {
                context.getConnection().close();
            }
        } catch (Exception e) {
                throw new RuntimeException("Error closing connection", e);
        } finally {
            requestContext.remove();
        }
    }
}
