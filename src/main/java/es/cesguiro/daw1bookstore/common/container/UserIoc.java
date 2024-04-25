package es.cesguiro.daw1bookstore.common.container;

import es.cesguiro.daw1bookstore.domain.service.UserService;
import es.cesguiro.daw1bookstore.domain.service.impl.UserServiceImpl;
import es.cesguiro.daw1bookstore.persistence.dao.UserDao;
import es.cesguiro.daw1bookstore.persistence.dao.impl.Memory.UserDaoMemory;
import es.cesguiro.daw1bookstore.persistence.dao.impl.jdbc.UserDaoJdbc;
import es.cesguiro.daw1bookstore.persistence.repository.UserRepository;
import es.cesguiro.daw1bookstore.persistence.repository.impl.UserRepositoryImpl;

public class UserIoc {

    private static UserService userService;
    private static UserRepository userRepository;
    private static UserDao userDao;

    public static UserService getUserService() {
        if (userService == null) {
            userService = new UserServiceImpl(getUserRepository());
        }
        return userService;
    }

    public static UserRepository getUserRepository() {
        if (userRepository == null) {
            userRepository = new UserRepositoryImpl(getUserDao());
        }
        return userRepository;
    }

    public static UserDao getUserDao() {
        if (userDao == null) {
            //userDao = new UserDaoMemory();
            userDao = new UserDaoJdbc();
        }
        return userDao;
    }

    public static void reset() {
        userRepository = null;
        userDao = null;
    }
}
