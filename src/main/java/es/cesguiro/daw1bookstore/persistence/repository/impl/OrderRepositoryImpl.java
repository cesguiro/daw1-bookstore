package es.cesguiro.daw1bookstore.persistence.repository.impl;

import es.cesguiro.daw1bookstore.common.container.OrderDetailIoc;
import es.cesguiro.daw1bookstore.domain.model.Order;
import es.cesguiro.daw1bookstore.domain.model.OrderDetail;
import es.cesguiro.daw1bookstore.persistence.dao.OrderDao;
import es.cesguiro.daw1bookstore.persistence.repository.OrderRepository;

import java.util.List;

public class OrderRepositoryImpl implements OrderRepository {

    private final OrderDao orderDao;

    public OrderRepositoryImpl(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    @Override
    public List<Order> findByUserId(Integer userId) {
        return orderDao.findByUserId(userId);
    }

    @Override
    public Order findById(Integer id) {
        Order order = orderDao.findById(id);
        if(order == null || order.getStatus() == 0) {
            return null;
        }
        order.setOrderDetailList(getOrderDetails(order));
        return order;
    }

    @Override
    public List<Order> findAll() {
        return orderDao.findAll();
    }

    @Override
    public Order findByIdAndUserId(Integer id, Integer userId) {
        Order order =  orderDao.findByIdAndUserId(id, userId);
        if(order == null || order.getStatus() == 0) {
            return null;
        }
        order.setOrderDetailList(getOrderDetails(order));
        return order;
    }

    private List<OrderDetail> getOrderDetails(Order order) {
        List<OrderDetail> orderDetailList = OrderDetailIoc.getOrderDetailDao().findByOrderId(order.getId());
        order.setOrderDetailList(orderDetailList);
        return orderDetailList;
    }
}
