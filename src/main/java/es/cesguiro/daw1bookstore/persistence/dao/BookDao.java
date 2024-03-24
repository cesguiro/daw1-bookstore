package es.cesguiro.daw1bookstore.persistence.dao;

import es.cesguiro.daw1bookstore.domain.model.Book;
import es.cesguiro.daw1bookstore.persistence.dao.entity.BookEntity;

import java.util.List;

public interface BookDao {

    public List<BookEntity> findAll();
}
