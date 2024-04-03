package es.cesguiro.daw1bookstore.unit.repository;

import es.cesguiro.daw1bookstore.common.container.OrderIoc;
import es.cesguiro.daw1bookstore.domain.model.Order;
import es.cesguiro.daw1bookstore.mock.dao.OrderDaoMock;
import es.cesguiro.daw1bookstore.persistence.repository.OrderRepository;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("OrderRepository Unit Tests")
public class OrderRepositoryUnitTest {

    private static OrderRepository orderRepository;

    @BeforeAll
    public static void setUp() {
        OrderIoc.setOrderDao(new OrderDaoMock());
        orderRepository = OrderIoc.getOrderRespository();
    }

    @DisplayName("Test find All orders by userId")
    @Test
    public void testFindByUserId() {
        int userId = 2;
        List<es.cesguiro.daw1bookstore.domain.model.Order> expectedOrderList = List.of(
                new Order(1, null, LocalDate.of(2023, 11, 30), LocalDate.of(2023, 12, 05), new BigDecimal(75.05), 4),
                new Order(2, null, LocalDate.of(2023, 02, 12), LocalDate.of(2023, 02, 15), new BigDecimal(190.00), 4),
                new Order(5, null, LocalDate.of(2024, 03, 29), null, new BigDecimal(125.50), 2)
        );
        List<Order> actualOrderList = orderRepository.findByUserId(userId);

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
        List<Order> actualOrderList = orderRepository.findByUserId(userId);

        assertAll("orders",
                () -> assertEquals(expectedOrderList.size(), actualOrderList.size(), "Tamaño del listado incorrecto")
        );
    }

    @DisplayName("Test find order by id")
    @Test
    public void testFindOrderById() {
        int orderId = 1;
        Order expectedOrder = new Order(1, null, LocalDate.of(2023, 11, 30), LocalDate.of(2023, 12, 05), new BigDecimal(75.05), 4);
        Order actualOrder = orderRepository.findById(orderId);

        assertEquals(expectedOrder, actualOrder, "Orden incorrecta");
    }

    @DisplayName("Test find order by non-existent id")
    @Test
    public void testFindOrderByNonExistentId() {
        int orderId = 100;
        Order actualOrder = orderRepository.findById(orderId);

        assertEquals(null, actualOrder, "Orden incorrecta");
    }

    @AfterAll
    public static void tearDown() {
        OrderIoc.reset();
    }
}
