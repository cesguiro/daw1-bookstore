package es.cesguiro.daw1.bookstore.web.factory;

import es.cesguiro.daw1.bookstore.application.handler.UserHandler;
import es.cesguiro.daw1.bookstore.application.handler.impl.UserHandlerImpl;
import es.cesguiro.daw1.bookstore.domain.repository.UserRepository;
import es.cesguiro.daw1.bookstore.domain.service.user.FindUserByCriteriaService;
import es.cesguiro.daw1.bookstore.domain.service.user.InsertUserService;
import es.cesguiro.daw1.bookstore.domain.service.auth.LoginService;
import es.cesguiro.daw1.bookstore.domain.usecase.user.FindUserByCriteriaUseCase;
import es.cesguiro.daw1.bookstore.domain.usecase.user.InsertUserUseCase;
import es.cesguiro.daw1.bookstore.domain.usecase.auth.LoginUseCase;
import es.cesguiro.daw1.bookstore.persistence.dao.UserDao;
import es.cesguiro.daw1.bookstore.persistence.dao.jdbc.UserDaoJdbc;
import es.cesguiro.daw1.bookstore.persistence.repository.UserRepositoryJdbc;
import es.cesguiro.daw1.bookstore.web.controller.Controller;
import es.cesguiro.daw1.bookstore.web.controller.UserController;

public class UserFactory {

    private static UserHandler userHandler;
    private static Controller userController;
    private static InsertUserUseCase insertUserUseCase;
    private static FindUserByCriteriaUseCase findUserByCriteriaUseCase;
    private static UserRepository userRepository;
    private static UserDao userDao;

    public static Controller userController() {
        if (userController == null) {
            userController = new UserController(userHandler(), AuthFactory.authHandler());
        }
        return userController;
    }

    public static UserHandler userHandler() {
        if (userHandler == null) {
            userHandler = new UserHandlerImpl(insertUserUseCase(), findUserByCriteriaUseCase());
        }
        return userHandler;
    }

    public static InsertUserUseCase insertUserUseCase() {
        if (insertUserUseCase == null) {
            insertUserUseCase = new InsertUserService(userRepository());
        }
        return insertUserUseCase;
    }

    public static FindUserByCriteriaUseCase findUserByCriteriaUseCase() {
        if (findUserByCriteriaUseCase == null) {
            findUserByCriteriaUseCase = new FindUserByCriteriaService(userRepository());
        }
        return findUserByCriteriaUseCase;
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
