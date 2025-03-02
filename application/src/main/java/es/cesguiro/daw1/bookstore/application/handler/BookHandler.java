package es.cesguiro.daw1.bookstore.application.handler;

import es.cesguiro.daw1.bookstore.application.PageResponse;
import es.cesguiro.daw1.bookstore.domain.model.Book;

public interface BookHandler {

    PageResponse<Book> findAll(Integer page, Integer size);

    Book findByIsbn(String isbn);

}
