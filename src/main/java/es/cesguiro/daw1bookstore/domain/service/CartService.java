package es.cesguiro.daw1bookstore.domain.service;

import es.cesguiro.daw1bookstore.domain.model.Cart;

public interface CartService {

    Cart findByUserId(Integer userId);
}
