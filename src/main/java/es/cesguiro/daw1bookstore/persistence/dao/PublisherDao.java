package es.cesguiro.daw1bookstore.persistence.dao;

import es.cesguiro.daw1bookstore.domain.model.Publisher;

public interface PublisherDao {


    Publisher findByBookId(Integer bookId);
}
