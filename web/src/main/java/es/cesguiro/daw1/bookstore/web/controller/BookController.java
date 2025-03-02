package es.cesguiro.daw1.bookstore.web.controller;


import es.cesguiro.daw1.bookstore.application.PageResponse;
import es.cesguiro.daw1.bookstore.application.handler.BookHandler;
import es.cesguiro.daw1.bookstore.domain.model.Book;
import es.cesguiro.daw1.bookstore.util.property.PropertyUtil;
import es.cesguiro.daw1.bookstore.web.factory.BookFactory;
import es.cesguiro.daw1.bookstore.web.thymeleaf.Template;
import es.cesguiro.daw1.bookstore.web.thymeleaf.TemplateThymeleaf;
import jakarta.servlet.http.*;

public class BookController {

    BookHandler bookHandler = BookFactory.bookHandler();
    Template template = new TemplateThymeleaf();

    public void findAll(HttpServletRequest request, HttpServletResponse response) {
        int page = request.getParameter("page") != null ?
                Integer.parseInt(request.getParameter("page")) :
                1;
        int size = request.getParameter("size") != null ?
                Integer.parseInt(request.getParameter("size")) :
                Integer.parseInt(PropertyUtil.getPropertyProvider().getProperty("page.size", "10"));
        PageResponse<Book> pageResponse = bookHandler.findAll(page, size);
        template.init(request, response, request.getServletContext());
        template.setVariable("pageResponse", pageResponse);
        template.process("books/list");
    }

    public void findByIsbn(HttpServletRequest request, HttpServletResponse response) {
        String isbn = (String) request.getAttribute("isbn");
        Book book = bookHandler.findByIsbn(isbn);
        template.init(request, response, request.getServletContext());
        template.setVariable("book", book);
        template.process("books/detail");
    }


}
