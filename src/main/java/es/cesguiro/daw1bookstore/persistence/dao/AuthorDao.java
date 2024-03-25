package es.cesguiro.daw1bookstore.persistence.dao;

import es.cesguiro.daw1bookstore.domain.model.Author;

import java.util.List;

public interface AuthorDao {

    List<Author> findByBookId(Integer bookId);
}
