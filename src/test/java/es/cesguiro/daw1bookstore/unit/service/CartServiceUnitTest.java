package es.cesguiro.daw1bookstore.unit.service;

import es.cesguiro.daw1bookstore.common.container.CartIoc;
import es.cesguiro.daw1bookstore.domain.model.Book;
import es.cesguiro.daw1bookstore.domain.model.Cart;
import es.cesguiro.daw1bookstore.domain.model.CartDetail;
import es.cesguiro.daw1bookstore.domain.model.User;
import es.cesguiro.daw1bookstore.domain.service.CartService;
import es.cesguiro.daw1bookstore.mock.repository.CartRepositoryMock;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("CartService Unit Tests")
public class CartServiceUnitTest {

    private static CartService cartService;

    @BeforeAll
    public static void setupAll() {
        CartIoc.setCartRepository(new CartRepositoryMock());
        cartService = CartIoc.getCartService();
    }

    @AfterAll
    public static void teardownAll() {
        CartIoc.reset();
    }

    @DisplayName("Test find cart by userId")
    @Test
    public void testFindCartByUserId() {
        int userId = 3;
        Cart expectedCart = new Cart(
                2,
                new User(3, null, null, null, null, null, null, null),
                new BigDecimal(0.00),
                List.of(
                        new CartDetail(14, new Book(1, "9788433920423", null, null, null, null), 1, new BigDecimal(13.20))
                )
        );
        Cart actualCart = cartService.findByUserId(userId);
        assertAll(
                () -> assertEquals(expectedCart, actualCart, "Carrito incorrecto"),
                () -> assertEquals(expectedCart.getUser(), actualCart.getUser(), "Usuario incorrecto"),
                () -> assertEquals(expectedCart.getCartDetailList(), actualCart.getCartDetailList(), "Lista de detalles incorrecta")
        );
    }

    @DisplayName("Test find cart by non-existent userId")
    @Test
    public void testFindCartByNonExistentUserId() {
        int userId = 100;
        Cart actualCart = cartService.findByUserId(userId);
        assertEquals(null, actualCart, "Carrito incorrecto");
    }
}
