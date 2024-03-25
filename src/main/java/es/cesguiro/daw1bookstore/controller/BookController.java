package es.cesguiro.daw1bookstore.controller;

import es.cesguiro.daw1bookstore.common.container.BookIoc;
import es.cesguiro.daw1bookstore.domain.model.Author;
import es.cesguiro.daw1bookstore.domain.model.Book;
import es.cesguiro.daw1bookstore.domain.model.Publisher;
import es.cesguiro.daw1bookstore.domain.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;
import java.util.List;

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
    public String findById(Model model, Integer id) {
        Book book =  new Book(
                5,
                "9788448022440",
                "Buenos presagios",
                "Buenos presagios es una novela de humor escrita por Terry Pratchett y Neil Gaiman. Fue publicada por primera vez en 1990. La novela es una narrativa de múltiples capas que se centra en la vida de cuatro personajes principales: Tomáš, Teresa, Sabina y Franz.",
                new BigDecimal(9.30),
                "buenosPresagios.jpeg",
                new Publisher(5, "Minotauro"),
                List.of(new Author(4, "Terry Pratchett"), new Author(5, "Neil Gaiman"))
        );
        model.addAttribute("book", book);
        return "books/detail";
    }
}
