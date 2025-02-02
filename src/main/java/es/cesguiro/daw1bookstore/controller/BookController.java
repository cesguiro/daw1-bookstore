package es.cesguiro.daw1bookstore.controller;

import es.cesguiro.daw1bookstore.common.container.BookIoc;
import es.cesguiro.daw1bookstore.domain.service.BookService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(BookController.URL)
public class BookController {

    public static final String URL = "/books";

    private final BookService bookService;

    public BookController() {
        this.bookService = BookIoc.getBookService();
    }

    @GetMapping
    public String findAll(Model model) {
        model.addAttribute("bookList", bookService.findAll());
        return "books/list";
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable(required = false) String language, @PathVariable int id, Model model) {
        model.addAttribute("book", bookService.findById(id));
        return "books/detail";
    }
}
