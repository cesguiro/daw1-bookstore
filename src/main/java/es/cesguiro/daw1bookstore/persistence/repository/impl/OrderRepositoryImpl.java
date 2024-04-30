package es.cesguiro.daw1bookstore.persistence.repository.impl;

import es.cesguiro.daw1bookstore.common.container.OrderDetailIoc;
import es.cesguiro.daw1bookstore.domain.model.CartDetail;
import es.cesguiro.daw1bookstore.domain.model.Order;
import es.cesguiro.daw1bookstore.domain.model.OrderDetail;
import es.cesguiro.daw1bookstore.persistence.dao.OrderDao;
import es.cesguiro.daw1bookstore.persistence.dao.OrderDetailDao;
import es.cesguiro.daw1bookstore.persistence.repository.OrderRepository;

import java.util.List;

public class OrderRepositoryImpl implements OrderRepository {

    private final OrderDao orderDao;

    public OrderRepositoryImpl(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    @Override
    public List<Order> findByUserId(Integer userId) {
        return orderDao.findOrderByUserId(userId);
    }

    @Override
    public Order findById(Integer id) {
        Order order = orderDao.findById(id);
        if(order == null || order.getStatus() == 0) {
            return null;
        }
        List<OrderDetail> orderDetailList = OrderDetailIoc.getOrderDetailDao().findByOrderId(order.getId());
        order.setOrderDetailList(orderDetailList);
        return order;
    }

    @Override
    public void save(Order order) {
        orderDao.insert(order);
        for (OrderDetail orderDetail : order.getOrderDetailList()) {
            OrderDetailIoc.getOrderDetailDao().insertCartDetailIntoCart(cart.getId(), cartDetail);
        }
    }
}
