package es.cesguiro.daw1bookstore.controller;

import es.cesguiro.daw1bookstore.domain.model.Author;
import es.cesguiro.daw1bookstore.domain.model.Book;
import es.cesguiro.daw1bookstore.domain.model.Publisher;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;
import java.util.List;

@Controller
@RequestMapping(BookController.URL)
public class BookController {

    List<Book> bookList = List.of(
            new Book(
                    1,
                    "9788433920423",
                    "La conjura de los necios",
                    "El protagonista de esta novela es uno de los personajes más memorables...",
                    new BigDecimal(13.20).setScale(2, BigDecimal.ROUND_HALF_UP),
                    "necios.jpeg",
                    new Publisher(1, "Editorial Anagrama"),
                    List.of(
                            new Author(1, "John Kennedy Toole")
                    )
            ),
            new Book(
                    2,
                    "9788426418197",
                    "El nombre de la rosa",
                    "Valiéndose de las características de la novela gótica, la crónica medieval y la ...",
                    new BigDecimal(12.30).setScale(2, BigDecimal.ROUND_HALF_UP),
                    "nombreRosa.jpeg",
                    new Publisher(2, "Penguin Random House Grupo Editorial España"),
                    List.of(
                            new Author(2, "Umbero Eco")
                    )
            )
    );

    public static final String URL = "/books";

    @GetMapping
    public String findAll(Model model) {
        model.addAttribute("bookList", bookList);
        return "books/list";
    }
}
