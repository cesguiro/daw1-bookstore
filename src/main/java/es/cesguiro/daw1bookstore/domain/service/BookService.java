package es.cesguiro.daw1bookstore.domain.service;

import es.cesguiro.daw1bookstore.domain.model.Book;

import java.util.List;

public interface BookService {

    public List<Book> findAll();
}
