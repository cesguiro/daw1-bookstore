package es.cesguiro.daw1bookstore.persistence.dao;

import es.cesguiro.daw1bookstore.domain.model.Book;

import java.util.List;

public interface BookDao {

    public List<Book> findAll();

    public Book findById(Integer id);
}
