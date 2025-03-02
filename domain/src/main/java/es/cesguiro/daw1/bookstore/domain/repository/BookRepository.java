package es.cesguiro.daw1.bookstore.domain.repository;

import es.cesguiro.daw1.bookstore.domain.model.Book;
import es.cesguiro.daw1.bookstore.util.pagination.Page;

import java.util.Optional;

public interface BookRepository {

    Page<Book> findAll(int page, int size);

    Optional<Book> findByIsbn(String isbn);

}
