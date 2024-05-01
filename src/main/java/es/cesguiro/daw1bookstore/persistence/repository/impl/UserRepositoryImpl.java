package es.cesguiro.daw1bookstore.persistence.repository.impl;

import es.cesguiro.daw1bookstore.common.container.OrderIoc;
import es.cesguiro.daw1bookstore.domain.model.Cart;
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
        User user = userDao.findByUsername(username);
        if(!user.isAdmin()) {
            Cart cart = OrderIoc.getOrderDao().findCartByUserId(user.getId());
            user.setCart(cart);
        }
        return user;
    }
}
