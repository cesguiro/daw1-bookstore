package es.cesguiro.daw1bookstore.persistence.repository;

import es.cesguiro.daw1bookstore.domain.model.Book;

import java.util.List;

public interface BookRepository {

    public List<Book> findAll();

    public Book findById(Integer id);

}
