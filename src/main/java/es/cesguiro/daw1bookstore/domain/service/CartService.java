package es.cesguiro.daw1bookstore.domain.service;

import es.cesguiro.daw1bookstore.domain.model.Cart;
import es.cesguiro.daw1bookstore.domain.model.CartDetail;

public interface CartService {

    Cart findByUserId(Integer userId);
    Cart addCartDetail(Cart cart, CartDetail cartDetail);
}
