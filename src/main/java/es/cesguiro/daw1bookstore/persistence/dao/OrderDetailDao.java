package es.cesguiro.daw1bookstore.persistence.dao;

import es.cesguiro.daw1bookstore.domain.model.CartDetail;
import es.cesguiro.daw1bookstore.domain.model.OrderDetail;

import java.util.List;

public interface OrderDetailDao {

    List<OrderDetail> findByOrderId(Integer orderId);

    List<CartDetail> findCartDetailListByCartId(Integer cartId);

    void insertCartDetailIntoCart(Integer cartId, CartDetail cartDetail);

    void deleteCartDetailListByCartId(Integer cartId);
}
