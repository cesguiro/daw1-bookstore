package es.cesguiro.daw1bookstore.integration.repository;

import es.cesguiro.daw1bookstore.common.AppPropertiesReader;
import es.cesguiro.daw1bookstore.common.container.CartIoc;
import es.cesguiro.daw1bookstore.common.container.OrderIoc;
import es.cesguiro.daw1bookstore.domain.model.Book;
import es.cesguiro.daw1bookstore.domain.model.Cart;
import es.cesguiro.daw1bookstore.domain.model.CartDetail;
import es.cesguiro.daw1bookstore.domain.model.User;
import es.cesguiro.daw1bookstore.domain.service.CartService;
import es.cesguiro.daw1bookstore.mock.repository.CartRepositoryMock;
import es.cesguiro.daw1bookstore.persistence.dao.OrderDao;
import es.cesguiro.daw1bookstore.persistence.dao.impl.jdbc.rawSql.RawSql;
import es.cesguiro.daw1bookstore.persistence.repository.CartRepository;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.*;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("CartRepositoryDao Integration Tests")
public class CartRepositoryDaoIntegrationTest {

    private static CartRepository cartRepository = CartIoc.getCartRepository();


    @BeforeAll
    public static void setupAll(){
        // Configuración de Flyway
        Flyway flyway = Flyway.configure().dataSource(
                AppPropertiesReader.getProperty("flyway.url"),
                AppPropertiesReader.getProperty("flyway.user"),
                AppPropertiesReader.getProperty("flyway.password")
        ).load();

        // Ejecución de migraciones
        flyway.migrate();
    }

    @AfterEach
    public void teardown(){
        RawSql.rollback();
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
        Cart actualCart = cartRepository.findByUserId(userId);
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
        Cart actualCart = cartRepository.findByUserId(userId);
        assertEquals(null, actualCart, "Carrito incorrecto");
    }
}
