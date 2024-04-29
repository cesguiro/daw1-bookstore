package es.cesguiro.daw1bookstore.unit.repository;

import es.cesguiro.daw1bookstore.common.container.CartIoc;
import es.cesguiro.daw1bookstore.common.container.OrderDetailIoc;
import es.cesguiro.daw1bookstore.common.container.OrderIoc;
import es.cesguiro.daw1bookstore.domain.model.Book;
import es.cesguiro.daw1bookstore.domain.model.Cart;
import es.cesguiro.daw1bookstore.domain.model.CartDetail;
import es.cesguiro.daw1bookstore.mock.dao.OrderDaoMock;
import es.cesguiro.daw1bookstore.mock.dao.OrderDetailDaoMock;
import es.cesguiro.daw1bookstore.persistence.repository.CartRepository;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("CartRepository Unit Tests")
public class CartRepositoryUnitTest {

    private static CartRepository cartRepository;

    @BeforeAll
    public static void setupAll() {
        OrderIoc.setOrderDao(new OrderDaoMock());
        OrderDetailIoc.setOrderDetailDao(new OrderDetailDaoMock());
        cartRepository = CartIoc.getCartRepository();
    }

    @AfterAll
    public static void teardownAll() {
        OrderIoc.reset();
        OrderDetailIoc.reset();
    }

    @DisplayName("Test find cart by userId")
    @Test
    public void testFindCartByUserId() {
        int userId = 2;
        Cart expectedCart = new Cart(1, null, new BigDecimal(0.00), null);
        Cart actualCart = cartRepository.findByUserId(userId);

        assertEquals(expectedCart, actualCart, "Carrito incorrecto");
    }

    @DisplayName("Test find cart by non-existent userId")
    @Test
    public void testFindCartByNonExistentUserId() {
        int userId = 100;
        Cart actualCart = cartRepository.findByUserId(userId);

        assertNull(actualCart, "Carrito incorrecto");
    }

    @DisplayName("Test save cart with empty cart")
    @Test
    public void testSaveCartWithEmptyCart() {
        //Probar que se ejecutan los diferentes métodos de los mocks
    }
}
