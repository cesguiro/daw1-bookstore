package es.cesguiro.daw1.bookstore.web.controller;


import es.cesguiro.daw1.bookstore.application.PageResponse;
import es.cesguiro.daw1.bookstore.application.handler.BookHandler;
import es.cesguiro.daw1.bookstore.domain.model.Book;
import es.cesguiro.daw1.bookstore.util.property.PropertyUtil;
import es.cesguiro.daw1.bookstore.web.factory.TemplateFactory;
import es.cesguiro.daw1.bookstore.web.router.Method;
import es.cesguiro.daw1.bookstore.web.router.Route;
import es.cesguiro.daw1.bookstore.web.router.Routes;
import es.cesguiro.daw1.bookstore.web.thymeleaf.Template;
import es.cesguiro.daw1.bookstore.web.thymeleaf.ThymeleafTemplate;
import jakarta.servlet.http.*;

public class BookController implements Controller {

    private final BookHandler bookHandler;
    private final Template template = TemplateFactory.getTemplate();

    @Override
    public void registerRoutes(Routes routes) {
        routes.add(new Route(Method.GET, "/books", this::findAll));
        routes.add(new Route(Method.GET, "/books/{isbn}", this::findByIsbn));
    }

    public BookController(BookHandler bookHandler) {
        this.bookHandler = bookHandler;
    }

    public void findAll(HttpServletRequest request, HttpServletResponse response) {
        int page = request.getParameter("page") != null ?
                Integer.parseInt(request.getParameter("page")) :
                1;
        int size = request.getParameter("size") != null ?
                Integer.parseInt(request.getParameter("size")) :
                Integer.parseInt(PropertyUtil.getPropertyProvider().getProperty("page.size", "10"));
        PageResponse<Book> pageResponse = bookHandler.findAll(page, size);
        template.setVariable("pageResponse", pageResponse);
        template.process("books/list");
    }

    public void findByIsbn(HttpServletRequest request, HttpServletResponse response) {
        String isbn = (String) request.getAttribute("isbn");
        Book book = bookHandler.findByIsbn(isbn);
        template.setVariable("book", book);
        template.process("books/detail");
    }
}
