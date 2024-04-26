package es.cesguiro.daw1bookstore.mock.repository;

import es.cesguiro.daw1bookstore.domain.model.*;
import es.cesguiro.daw1bookstore.persistence.repository.CartRepository;

import java.math.BigDecimal;
import java.util.List;

public class CartRepositoryMock implements CartRepository {

    private List<User> userList = List.of(
            new User(1, null, null, null, null, null, null, null),
            new User(2, null, null, null, null, null, null, null),
            new User(3, null, null, null, null, null, null, null)
    );
    private final List<Cart> cartList = List.of(
            new Cart(1, userList.get(1), new BigDecimal(0), null),
            new Cart(
                    2,
                    userList.get(2),
                    new BigDecimal(0),
                    List.of(
                            new CartDetail(14, new Book(1, "9788433920423", null, null, null, null), 1, new BigDecimal(13.20))
                    )
            )
    );

    @Override
    public Cart findByUserId(Integer userId) {
        for (Cart cart : cartList) {
            if (cart.getUser().getId() == userId) {
                return cart;
            }
        }
        return null;
    }

}
