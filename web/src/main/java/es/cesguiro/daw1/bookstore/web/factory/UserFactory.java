package es.cesguiro.daw1.bookstore.web.factory;

import es.cesguiro.daw1.bookstore.domain.repository.UserRepository;
import es.cesguiro.daw1.bookstore.domain.service.user.InsertUserService;
import es.cesguiro.daw1.bookstore.domain.usecase.user.InsertUserUseCase;
import es.cesguiro.daw1.bookstore.persistence.dao.UserDao;
import es.cesguiro.daw1.bookstore.persistence.dao.jdbc.UserDaoJdbc;
import es.cesguiro.daw1.bookstore.persistence.repository.UserRepositoryJdbc;

public class UserFactory {

    private static InsertUserUseCase insertUserUseCase;
    private static UserRepository userRepository;
    private static UserDao userDao;

    public static InsertUserUseCase insertUserUseCase() {
        if (insertUserUseCase == null) {
            insertUserUseCase = new InsertUserService(userRepository());
        }
        return insertUserUseCase;
    }

    public static UserRepository userRepository() {
        if (userRepository == null) {
            userRepository = new UserRepositoryJdbc(userDao());
        }
        return userRepository;
    }

    public static UserDao userDao() {
        if (userDao == null) {
            userDao = new UserDaoJdbc();
        }
        return userDao;
    }
}
