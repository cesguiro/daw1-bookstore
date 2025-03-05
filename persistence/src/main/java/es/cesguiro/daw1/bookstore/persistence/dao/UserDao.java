package es.cesguiro.daw1.bookstore.persistence.dao;

import es.cesguiro.daw1.bookstore.domain.model.User;
import es.cesguiro.daw1.bookstore.persistence.dao.jdbc.model.UserRecord;

import java.util.Optional;

public interface UserDao extends GenericDao<UserRecord, Long> {

    Optional<UserRecord> findByEmail(String email);

}
