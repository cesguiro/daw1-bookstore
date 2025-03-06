package es.cesguiro.daw1.bookstore.persistence.dao;

import es.cesguiro.daw1.bookstore.persistence.dao.jdbc.model.UserRecord;

import java.util.Optional;

public interface UserDao extends GenericDao<UserRecord, Long> {

    Optional<UserRecord> findByEmail(String email);

    void storeToken(UserRecord userRecord, String token);

    Optional<UserRecord> findByToken(String token);
}
