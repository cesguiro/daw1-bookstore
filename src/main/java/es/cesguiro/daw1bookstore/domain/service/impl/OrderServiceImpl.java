package es.cesguiro.daw1bookstore.domain.service.impl;

import es.cesguiro.daw1bookstore.domain.model.Order;
import es.cesguiro.daw1bookstore.domain.service.OrderService;
import es.cesguiro.daw1bookstore.persistence.repository.OrderRepository;

import java.util.List;

public class OrderServiceImpl implements OrderService {


    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public List<Order> findByUserId(Integer userId) {
        return orderRepository.findByUserId(userId);
    }
}
