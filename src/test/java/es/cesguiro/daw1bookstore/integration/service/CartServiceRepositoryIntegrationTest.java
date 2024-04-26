package es.cesguiro.daw1bookstore.integration.service;

import es.cesguiro.daw1bookstore.common.container.BookIoc;
import es.cesguiro.daw1bookstore.common.container.CartIoc;
import es.cesguiro.daw1bookstore.common.container.OrderDetailIoc;
import es.cesguiro.daw1bookstore.common.container.OrderIoc;
import es.cesguiro.daw1bookstore.domain.model.Book;
import es.cesguiro.daw1bookstore.domain.model.Cart;
import es.cesguiro.daw1bookstore.domain.model.CartDetail;
import es.cesguiro.daw1bookstore.domain.model.User;
import es.cesguiro.daw1bookstore.domain.service.CartService;
import es.cesguiro.daw1bookstore.mock.dao.OrderDaoMock;
import es.cesguiro.daw1bookstore.mock.dao.OrderDetailDaoMock;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("CartService Repository Integration Tests")
public class CartServiceRepositoryIntegrationTest {

    private static CartService cartService;

    @BeforeAll
    public static void setupAll() {
        OrderIoc.setOrderDao(new OrderDaoMock());
        OrderDetailIoc.setOrderDetailDao(new OrderDetailDaoMock());
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
