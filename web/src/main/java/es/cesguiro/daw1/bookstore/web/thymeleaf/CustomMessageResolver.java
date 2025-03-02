package es.cesguiro.daw1.bookstore.web.thymeleaf;

import org.thymeleaf.context.ITemplateContext;
import org.thymeleaf.messageresolver.IMessageResolver;

public class CustomMessageResolver implements IMessageResolver {

    @Override
    public String getName() {
        return "";
    }

    @Override
    public Integer getOrder() {
        return 0;
    }

    @Override
    public String resolveMessage(ITemplateContext context, Class<?> origin, String key, Object[] messageParameters) {
        return CustomMessageSource.getMessage(key);
    }

    @Override
    public String createAbsentMessageRepresentation(ITemplateContext context, Class<?> origin, String key, Object[] messageParameters) {
        return "";
    }
}
