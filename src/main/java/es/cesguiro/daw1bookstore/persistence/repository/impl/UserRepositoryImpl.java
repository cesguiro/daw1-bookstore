package es.cesguiro.daw1bookstore.persistence.repository.impl;

import es.cesguiro.daw1bookstore.domain.model.User;
import es.cesguiro.daw1bookstore.persistence.dao.UserDao;
import es.cesguiro.daw1bookstore.persistence.repository.UserRepository;

public class UserRepositoryImpl implements UserRepository {

    private final UserDao userDao;

    public UserRepositoryImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }
}
