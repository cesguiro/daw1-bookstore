package es.cesguiro.daw1.bookstore.domain.repository;


import es.cesguiro.daw1.bookstore.domain.model.Author;

import java.util.List;

public interface AuthorRepository {

    List<Author> findAllByBookIsbn(String isbn);
}
