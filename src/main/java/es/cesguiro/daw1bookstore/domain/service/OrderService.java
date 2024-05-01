package es.cesguiro.daw1bookstore.domain.service;

import es.cesguiro.daw1bookstore.domain.model.Order;

import java.util.List;

public interface OrderService {

    List<Order> findByUserId(Integer userId);

    Order findById(Integer id);

    List<Order> findAll();
}
