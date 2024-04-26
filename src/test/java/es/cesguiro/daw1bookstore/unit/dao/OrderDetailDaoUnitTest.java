package es.cesguiro.daw1bookstore.unit.dao;

import es.cesguiro.daw1bookstore.common.AppPropertiesReader;
import es.cesguiro.daw1bookstore.common.container.OrderDetailIoc;
import es.cesguiro.daw1bookstore.domain.model.Book;
import es.cesguiro.daw1bookstore.domain.model.CartDetail;
import es.cesguiro.daw1bookstore.domain.model.OrderDetail;
import es.cesguiro.daw1bookstore.persistence.dao.OrderDetailDao;
import es.cesguiro.daw1bookstore.persistence.dao.impl.jdbc.rawSql.RawSql;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrderDetailDaoUnitTest {

    private final OrderDetailDao orderDetailDao = OrderDetailIoc.getOrderDetailDao();

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

    @DisplayName("Test find OrderDetail by OrderId")
    @Test
    public void testFindOrderDetailByOrderId() {
        int orderId = 3;
        List<OrderDetail> expectedOrderDetailList = List.of(
                new OrderDetail(1, new Book(2, "9788426418197", null, null, null, null), 2, new BigDecimal(12.30)),
                new OrderDetail(2, new Book(4, "9788466338141", null, null, null, null), 3, new BigDecimal(20.30))
        );
        List<OrderDetail> actualOrderDetailList = orderDetailDao.findByOrderId(orderId);

        assertAll(
                "orderDetails",
                () -> assertEquals(expectedOrderDetailList.size(), actualOrderDetailList.size(), "Tamaño del listado incorrecto"),
                () -> assertEquals(expectedOrderDetailList.get(0), actualOrderDetailList.get(0), "Detalle 1 de orden incorrecto"),
                () -> assertEquals(expectedOrderDetailList.get(1), actualOrderDetailList.get(1), "Detalle 2 de orden incorrecto")
        );
    }

    @DisplayName("Test find OrderDetail by non existent OrderId")
    @Test
    public void testFindOrderDetailByNonExistentOrderId() {
        int orderId = 100;
        List<OrderDetail> actualOrderDetailList = orderDetailDao.findByOrderId(orderId);

        assertEquals(0, actualOrderDetailList.size(), "Tamaño del listado incorrecto");
    }

    @DisplayName("Test find CartDetail by CartId without details")
    @Test
    public void testFindCartDetailListByCartIdWithoutDetails() {
        int cartId = 1;
        List<CartDetail> expectedCartDetailList = List.of();
        List<CartDetail> actualCartDetailList = orderDetailDao.findCartDetailListByCartId(cartId);
        assertEquals(expectedCartDetailList, actualCartDetailList, "Tamaño del listado incorrecto");
    }

    @DisplayName("Test find CartDetail by CartId with details")
    @Test
    public void TestFindCartDetailListByCartIdWithDetails() {
        int cartId = 2;
        List<CartDetail> expectedCartDetailList = List.of(
                new CartDetail(14, new Book(2, "9788426418197", null, null, null, null), 2, new BigDecimal(12.30))
        );
        List<CartDetail> actualCartDetailList = orderDetailDao.findCartDetailListByCartId(cartId);
        assertAll(
                "cartDetails",
                () -> assertEquals(expectedCartDetailList.size(), actualCartDetailList.size(), "Tamaño del listado incorrecto"),
                () -> assertEquals(expectedCartDetailList.get(0), actualCartDetailList.get(0), "Detalle de carrito incorrecto")
        );
    }

}
