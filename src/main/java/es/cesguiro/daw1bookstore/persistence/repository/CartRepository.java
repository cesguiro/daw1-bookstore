package es.cesguiro.daw1bookstore.persistence.repository;

import es.cesguiro.daw1bookstore.domain.model.Cart;
import es.cesguiro.daw1bookstore.persistence.dao.OrderDao;

public interface CartRepository {

    Cart findByUserId(Integer userId);

    void save(Cart cart);
}
