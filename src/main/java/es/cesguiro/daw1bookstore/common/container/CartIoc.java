package es.cesguiro.daw1bookstore.common.container;

import es.cesguiro.daw1bookstore.domain.service.CartService;
import es.cesguiro.daw1bookstore.domain.service.impl.CartServiceImpl;
import es.cesguiro.daw1bookstore.persistence.repository.CartRepository;
import es.cesguiro.daw1bookstore.persistence.repository.impl.CartRepositoryImpl;

public class CartIoc {

    private static CartService cartService;
    private static CartRepository cartRepository;

    public static CartService getCartService() {
        if(cartService == null) {
            cartService = new CartServiceImpl(getCartRepository());
        }
        return cartService;
    }

    public static CartRepository getCartRepository() {
        if(cartRepository == null) {
            cartRepository = new CartRepositoryImpl(OrderIoc.getOrderDao());
        }
        return cartRepository;
    }

    public static void setCartService(CartService cartService) {
        CartIoc.cartService = cartService;
    }

    public static void setCartRepository(CartRepository cartRepository) {
        CartIoc.cartRepository = cartRepository;
    }

    public static void reset() {
        cartService = null;
        cartRepository = null;
    }
}
