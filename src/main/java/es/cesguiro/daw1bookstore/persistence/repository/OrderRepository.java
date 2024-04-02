package es.cesguiro.daw1bookstore.persistence.repository;

import es.cesguiro.daw1bookstore.domain.model.Order;

import java.util.List;

public interface OrderRepository {
    List<Order> findByUserId(Integer userId);
}
