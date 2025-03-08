package es.cesguiro.daw1.bookstore.web.factory;

import es.cesguiro.daw1.bookstore.application.handler.UserHandler;
import es.cesguiro.daw1.bookstore.application.handler.impl.UserHandlerImpl;
import es.cesguiro.daw1.bookstore.domain.repository.UserRepository;
import es.cesguiro.daw1.bookstore.domain.service.user.FindUserByCriteriaService;
import es.cesguiro.daw1.bookstore.domain.service.user.InsertUserService;
import es.cesguiro.daw1.bookstore.domain.usecase.user.FindUserByCriteriaUseCase;
import es.cesguiro.daw1.bookstore.domain.usecase.user.InsertUserUseCase;
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

    public static Controller createUserController() {
        if (userController == null) {
            userController = new UserController(createUserHandler(), AuthFactory.createAuthHandler());
        }
        return userController;
    }

    public static UserHandler createUserHandler() {
        if (userHandler == null) {
            userHandler = new UserHandlerImpl(createInsertUserUseCase(), createFindUserByCriteriaUseCase());
        }
        return userHandler;
    }

    public static InsertUserUseCase createInsertUserUseCase() {
        if (insertUserUseCase == null) {
            insertUserUseCase = new InsertUserService(createUserRepository());
        }
        return insertUserUseCase;
    }

    public static FindUserByCriteriaUseCase createFindUserByCriteriaUseCase() {
        if (findUserByCriteriaUseCase == null) {
            findUserByCriteriaUseCase = new FindUserByCriteriaService(createUserRepository());
        }
        return findUserByCriteriaUseCase;
    }

    public static UserRepository createUserRepository() {
        if (userRepository == null) {
            userRepository = new UserRepositoryJdbc(createUserDao());
        }
        return userRepository;
    }

    public static UserDao createUserDao() {
        if (userDao == null) {
            userDao = new UserDaoJdbc();
        }
        return userDao;
    }

}
