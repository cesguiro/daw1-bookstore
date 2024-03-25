package es.cesguiro.daw1bookstore.persistence.repository;

import es.cesguiro.daw1bookstore.domain.model.Author;

import java.util.List;

public interface AuthorRepository {

    List<Author> findByBookId(Integer bookId);
}
