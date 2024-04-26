package es.cesguiro.daw1bookstore.unit.dao;

import es.cesguiro.daw1bookstore.common.AppPropertiesReader;
import es.cesguiro.daw1bookstore.common.container.OrderIoc;
import es.cesguiro.daw1bookstore.domain.model.Book;
import es.cesguiro.daw1bookstore.domain.model.Cart;
import es.cesguiro.daw1bookstore.domain.model.Order;
import es.cesguiro.daw1bookstore.domain.model.OrderDetail;
import es.cesguiro.daw1bookstore.persistence.dao.OrderDao;
import es.cesguiro.daw1bookstore.persistence.dao.impl.Memory.data.record.BookRecord;
import es.cesguiro.daw1bookstore.persistence.dao.impl.jdbc.rawSql.RawSql;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("OrderDao Unit Tests")
public class OrderDaoUnitTest {

    private final OrderDao orderDao = OrderIoc.getOrderDao();

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

    @DisplayName("Test find All orders by userId")
    @Test
    public void testFindOrdersByUserId() {
        int userId = 2;
        List<Order> expectedOrderList = List.of(
                new Order(3, null, LocalDate.of(2023, 11, 30), LocalDate.of(2023, 12, 05), new BigDecimal(75.05), 4),
                new Order(4, null, LocalDate.of(2023, 02, 12), LocalDate.of(2023, 02, 15), new BigDecimal(190.00), 4),
                new Order(7, null, LocalDate.of(2024, 03, 29), null, new BigDecimal(125.50), 2)
        );
        List<Order> actualOrderList = orderDao.findOrderByUserId(userId);

        assertAll("orders",
                () -> assertEquals(expectedOrderList.size(), actualOrderList.size(), "Tamaño del listado incorrecto"),
                () -> assertEquals(expectedOrderList.get(0), actualOrderList.get(0), "Orden incorrecta"),
                () -> assertEquals(expectedOrderList.get(1), actualOrderList.get(1), "Orden incorrecta"),
                () -> assertEquals(expectedOrderList.get(2), actualOrderList.get(2), "Orden incorrecta")
        );
    }

    @DisplayName("Test find All orders by userId with no orders")
    @Test
    public void testFindOrdersByUserIdWithNoOrders() {
        int userId = 19;
        List<Order> expectedOrderList = List.of();
        List<Order> actualOrderList = orderDao.findOrderByUserId(userId);

        assertAll("orders",
                () -> assertEquals(expectedOrderList, actualOrderList, "Tamaño del listado incorrecto")
        );
    }

    @DisplayName("Test find order by id")
    @Test
    public void testFindOrderById() {
        int orderId = 3;

        Order expectedOrder = new Order(3, null, LocalDate.of(2023, 11, 30), LocalDate.of(2023, 12, 05), new BigDecimal(75.05), 4);
        Order actualOrder = orderDao.findById(orderId);

        assertAll(
                () -> assertEquals(expectedOrder, actualOrder, "Id incorrecto"),
                () -> assertEquals(expectedOrder.getOrderDate(), actualOrder.getOrderDate(), "Fecha de pedido incorrecta"),
                () -> assertEquals(expectedOrder.getDeliveryDate(), actualOrder.getDeliveryDate(), "Fecha de entrega incorrecta")
        );
        assertEquals(expectedOrder, actualOrder, "Orden incorrecta");
    }

    @DisplayName("Test find order by non-existent id")
    @Test
    public void testFindOrderByNonExistentId() {
        int orderId = 100;
        Order actualOrder = orderDao.findById(orderId);

        assertNull(actualOrder, "Orden incorrecta");
    }

    @DisplayName("Test find cart by userId")
    @Test
    public void testFindCartByUserId() {
        int userId = 2;
        Cart expectedCart = new Cart(1, null, new BigDecimal(0.00));
        Cart actualCart = orderDao.findCartByUserId(userId);

        assertEquals(expectedCart, actualCart, "Carrito incorrecto");
    }

    @DisplayName("Test find cart by non-existent userId")
    @Test
    public void testFindCartByNonExistentUserId() {
        int userId = 100;
        Cart actualCart = orderDao.findCartByUserId(userId);

        assertNull(actualCart, "Carrito incorrecto");
    }
}
