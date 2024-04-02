package es.cesguiro.daw1bookstore.persistence.repository.impl;

import es.cesguiro.daw1bookstore.domain.model.Order;
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
}
