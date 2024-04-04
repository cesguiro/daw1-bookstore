package es.cesguiro.daw1bookstore.persistence.dao;

import es.cesguiro.daw1bookstore.domain.model.Order;

import java.util.List;

public interface OrderDao {
    List<Order> findOrderByUserId(Integer userId);

    Order findById(Integer id);
}
