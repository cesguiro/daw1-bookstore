package es.cesguiro.daw1bookstore.persistence.repository.impl;

import es.cesguiro.daw1bookstore.common.container.OrderDetailIoc;
import es.cesguiro.daw1bookstore.domain.model.Cart;
import es.cesguiro.daw1bookstore.domain.model.CartDetail;
import es.cesguiro.daw1bookstore.domain.model.Order;
import es.cesguiro.daw1bookstore.persistence.dao.OrderDao;
import es.cesguiro.daw1bookstore.persistence.repository.CartRepository;

import java.util.List;

public class CartRepositoryImpl implements CartRepository {

    private final OrderDao orderDao;

    public CartRepositoryImpl(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    @Override
    public Cart findByUserId(Integer userId) {
        Cart cart = orderDao.findCartByUserId(userId);
        if(cart == null) {
            return null;
        }
        List<CartDetail> cartDetailList = OrderDetailIoc.getOrderDetailDao().findCartDetailListByCartId(cart.getId());
        cart.setCartDetailList(cartDetailList);
        return cart;
    }

    @Override
    public void save(Cart cart) {
        orderDao.update(cart);
        OrderDetailIoc.getOrderDetailDao().deleteCartDetailListByCartId(cart.getId());
        for (CartDetail cartDetail : cart.getCartDetailList()) {
            OrderDetailIoc.getOrderDetailDao().insertCartDetailIntoCart(cart.getId(), cartDetail);
        }
    }

    @Override
    public Cart findById(int id) {
        Cart cart = orderDao.findCartById(id);
        if(cart == null) {
            return null;
        }
        List<CartDetail> cartDetailList = OrderDetailIoc.getOrderDetailDao().findCartDetailListByCartId(cart.getId());
        cart.setCartDetailList(cartDetailList);
        return cart;
    }
}
