package es.cesguiro.daw1.bookstore.persistence.dao;

import es.cesguiro.daw1.bookstore.persistence.dao.jdbc.model.PublisherRecord;

import java.util.Optional;

public interface PublisherDao extends GenericDao<PublisherRecord> {

    Optional<PublisherRecord> findByBookIsbn(String isbn);
}
