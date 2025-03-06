package es.cesguiro.daw1.bookstore.persistence.repository;

import es.cesguiro.daw1.bookstore.domain.model.User;
import es.cesguiro.daw1.bookstore.domain.repository.UserRepository;
import es.cesguiro.daw1.bookstore.persistence.dao.UserDao;
import es.cesguiro.daw1.bookstore.persistence.repository.mapper.UserMapper;

import java.util.Optional;

public class UserRepositoryJdbc implements UserRepository {

    private final UserDao userDao;

    public UserRepositoryJdbc(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userDao.findByEmail(email).map(UserMapper::toUser);
    }

    @Override
    public User save(User user) {
        return UserMapper.toUser(userDao.save(UserMapper.toUserRecord(user)));
    }

    @Override
    public void storeToken(User user, String token) {
        userDao.storeToken(UserMapper.toUserRecord(user), token);
    }

    @Override
    public Optional<User> findByToken(String token) {
        return userDao.findByToken(token).map(UserMapper::toUser);
    }
}
