package es.cesguiro.daw1bookstore.persistence.dao;

import es.cesguiro.daw1bookstore.domain.model.User;

public interface UserDao {

    User findByUsername(String username);
}
