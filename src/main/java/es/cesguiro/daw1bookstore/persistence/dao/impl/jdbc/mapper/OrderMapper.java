package es.cesguiro.daw1bookstore.persistence.dao.impl.jdbc.mapper;

import es.cesguiro.daw1bookstore.domain.model.Order;
import es.cesguiro.daw1bookstore.domain.model.User;
import es.cesguiro.daw1bookstore.persistence.dao.OrderDetailDao;
import es.cesguiro.daw1bookstore.persistence.dao.impl.Memory.OrderDetailDaoMemory;
import es.cesguiro.daw1bookstore.persistence.dao.impl.Memory.mapper.OrderDetailMapper;
import es.cesguiro.daw1bookstore.persistence.dao.impl.jdbc.OrderDetailDaoJdbc;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OrderMapper {

    public static Order toOrder(ResultSet resultSet) throws SQLException {
        if(resultSet == null) {
            return null;
        }
        LocalDate orderDate = resultSet.getDate("order_date") != null ? resultSet.getDate("order_date").toLocalDate() : null;
        LocalDate deliveryDate = resultSet.getDate("delivery_date") != null ? resultSet.getDate("delivery_date").toLocalDate() : null;
        BigDecimal total = resultSet.getString("total") != null ? new BigDecimal(resultSet.getString("total")) : new BigDecimal(0);
        Order order = new Order(
               resultSet.getInt("id"),
               orderDate,
               deliveryDate,
               total,
               resultSet.getInt("status")
        );
        User user = new User(
                resultSet.getInt("users.id"),
                resultSet.getString("users.username"),
                resultSet.getString("users.password"),
                resultSet.getString("users.email"),
                resultSet.getString("users.name"),
                resultSet.getString("users.surname"),
                resultSet.getString("users.address"),
                resultSet.getBoolean("users.admin")
        );
        order.setUser(user);
        return order;
    }

    public static Order toOrderWithOrderDetailList(ResultSet resultSet) throws SQLException {
        if(resultSet == null) {
            return null;
        }
        Order order = toOrder(resultSet);
        OrderDetailDao orderDetailDao= new OrderDetailDaoJdbc();
        order.setOrderDetailList(orderDetailDao.findByOrderId(order.getId()));
        return order;
    }

    public static List<Order> toOrderList(ResultSet resultSet) throws SQLException {
        List<Order> orderList = new ArrayList<>();
        while (resultSet.next()) {
            orderList.add(toOrder(resultSet));
        }
        return orderList;
    }
}
