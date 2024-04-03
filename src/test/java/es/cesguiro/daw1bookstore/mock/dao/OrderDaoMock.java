package es.cesguiro.daw1bookstore.mock.dao;

import es.cesguiro.daw1bookstore.domain.model.Order;
import es.cesguiro.daw1bookstore.domain.model.User;
import es.cesguiro.daw1bookstore.persistence.dao.OrderDao;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OrderDaoMock implements OrderDao {

    private List<User> userList = List.of(
            new User(1, null, null, null, null, null, null, null),
            new User(2, null, null, null, null, null, null, null),
            new User(3, null, null, null, null, null, null, null)
    );
    private final List<Order> orderList = List.of(
            new Order(1, userList.get(1), LocalDate.of(2023, 11, 30), LocalDate.of(2023, 12, 05), new BigDecimal(75.05), 4),
            new Order(2, userList.get(1), LocalDate.of(2023, 02, 12), LocalDate.of(2023, 02, 15), new BigDecimal(190.00), 4),
            new Order(3, userList.get(2), LocalDate.of(2024, 03, 20), LocalDate.of(2024, 03, 25), new BigDecimal(25.75), 4),
            new Order(4, userList.get(2), LocalDate.of(2024, 03, 25), null, new BigDecimal(50.05), 3),
            new Order(5, userList.get(1), LocalDate.of(2024, 03, 29), null, new BigDecimal(125.50), 2),
            new Order(6, userList.get(2), LocalDate.of(2024, 04, 01), null, new BigDecimal(75.25), 1)
    );

    @Override
    public List<Order> findByUserId(Integer userId) {
        List<Order> result = new ArrayList<>();
        for (Order order : orderList) {
            if (order.getUser().getId() == userId) {
                result.add(order);
            }
        }
        return result;
    }

    @Override
    public Order findById(Integer id) {
        for (Order order : orderList) {
            if (order.getId() == id) {
                return order;
            }
        }
        return null;
    }
}
