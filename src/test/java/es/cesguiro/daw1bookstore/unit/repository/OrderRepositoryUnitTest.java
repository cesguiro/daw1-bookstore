package es.cesguiro.daw1bookstore.unit.repository;

import es.cesguiro.daw1bookstore.common.container.OrderIoc;
import es.cesguiro.daw1bookstore.domain.model.Book;
import es.cesguiro.daw1bookstore.domain.model.Order;
import es.cesguiro.daw1bookstore.domain.model.OrderDetail;
import es.cesguiro.daw1bookstore.mock.dao.OrderDaoMock;
import es.cesguiro.daw1bookstore.persistence.repository.OrderRepository;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

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
    public void testFindOrdersByUserId() {
        int userId = 2;
        List<Order> expectedOrderList = List.of(
                new Order(3, null, LocalDate.of(2023, 11, 30), LocalDate.of(2023, 12, 05), new BigDecimal(75.05), 4),
                new Order(4, null, LocalDate.of(2023, 02, 12), LocalDate.of(2023, 02, 15), new BigDecimal(190.00), 4),
                new Order(7, null, LocalDate.of(2024, 03, 29), null, new BigDecimal(125.50), 2)
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
                () -> assertEquals(expectedOrderList, actualOrderList, "Tamaño del listado incorrecto")
        );
    }

    @DisplayName("Test find order by id")
    @Test
    public void testFindOrderById() {
        int orderId = 3;
        Book book1 = new Book(
                2,
                "9788426418197",
                "El nombre de la rosa",
                "Valiéndose de las características de la novela gótica, la crónica medieval y la novela policíaca, El nombre de la rosa narra las...",
                new BigDecimal(12.30),
                "nombreRosa.jpeg"
        );
        Book book2 = new Book(
                4,
                "9788466338141",
                "La isla del día de antes",
                "La isla del día de antes es una novela del escritor italiano Umberto Eco publicada en 1994. La novela es una exploración filosófica de la vida...",
                new BigDecimal(10.40),
                "islaDiaAntes.jpeg"
        );
        List<OrderDetail> orderDetailList = List.of(
                new OrderDetail(1, book1, 2, new BigDecimal(12.30)),
                new OrderDetail(2, book2, 3, new BigDecimal(20.30))
        );
        Order expectedOrder = new Order(3, null, LocalDate.of(2023, 11, 30), LocalDate.of(2023, 12, 05), new BigDecimal(75.05), 4);
        expectedOrder.setOrderDetailList(orderDetailList);
        Order actualOrder = orderRepository.findById(orderId);

        assertAll(
                () -> assertEquals(expectedOrder, actualOrder, "Id incorrecto"),
                () -> assertEquals(expectedOrder.getOrderDetailList().size(), actualOrder.getOrderDetailList().size(), "Detalles de la orden incorrecto"),
                () -> assertEquals(expectedOrder.getOrderDetailList().get(0), actualOrder.getOrderDetailList().get(0), "Libro 1 de la orden incorrecto"),
                () -> assertEquals(expectedOrder.getOrderDetailList().get(1), actualOrder.getOrderDetailList().get(1), "Libro 2 de la orden incorrecto")
        );
        assertEquals(expectedOrder, actualOrder, "Orden incorrecta");
    }

    @DisplayName("Test find order by non-existent id")
    @Test
    public void testFindOrderByNonExistentId() {
        int orderId = 100;
        Order actualOrder =orderRepository.findById(orderId);

        assertNull(actualOrder, "Orden incorrecta");
    }

    @AfterAll
    public static void tearDown() {
        OrderIoc.reset();
    }
}
