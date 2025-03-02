package es.cesguiro.daw1.bookstore.persistence.dao;

import es.cesguiro.daw1.bookstore.persistence.dao.jdbc.model.AuthorRecord;

import java.util.List;

public interface AuthorDao extends GenericDao<AuthorRecord> {

    List<AuthorRecord> findAllByBookIsbn(String isbn);
}
