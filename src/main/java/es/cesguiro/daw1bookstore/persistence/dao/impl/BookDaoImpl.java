package es.cesguiro.daw1bookstore.persistence.dao.impl;

import es.cesguiro.daw1bookstore.domain.model.Book;
import es.cesguiro.daw1bookstore.persistence.dao.BookDao;

import java.util.List;

public class BookDaoMock implements BookDao {
    @Override
    public List<Book> findAll() {
        return null;
    }
}
