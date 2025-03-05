package es.cesguiro.daw1.bookstore.web.factory;

import es.cesguiro.daw1.bookstore.web.thymeleaf.Template;
import es.cesguiro.daw1.bookstore.web.thymeleaf.ThymeleafTemplate;

public class TemplateFactory {

    private static Template template;

    public static Template getTemplate() {
        if (template == null) {
            template = new ThymeleafTemplate();
        }
        return template;
    }

}
