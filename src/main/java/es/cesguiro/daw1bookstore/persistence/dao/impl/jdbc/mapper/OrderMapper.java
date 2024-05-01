package es.cesguiro.daw1bookstore.persistence.dao.impl.jdbc.mapper;

import es.cesguiro.daw1bookstore.common.container.UserIoc;
import es.cesguiro.daw1bookstore.domain.model.Cart;
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
        return order;
    }

    public static List<Order> toOrderList(ResultSet resultSet) throws SQLException {
        List<Order> orderList = new ArrayList<>();
        while (resultSet.next()) {
            orderList.add(toOrder(resultSet));
        }
        return orderList;
    }

    public static Cart toCart(ResultSet resultSet) throws SQLException {
        /*if(resultSet == null) {
            return null;
        }
        Cart cart = new Cart(
                resultSet.getInt("id"),
                getUser(resultSet),
                resultSet.getBigDecimal("total")
        );
        return cart;*/
        return null;
    }

    private static User getUser(ResultSet resultSet) throws SQLException {
        if(resultSet == null) {
            return null;
        }
        return new User(
                resultSet.getInt("users.id"),
                resultSet.getString("users.username"),
                resultSet.getString("users.password"),
                resultSet.getString("users.email"),
                resultSet.getString("users.name"),
                resultSet.getString("users.surname"),
                resultSet.getString("users.address"),
                resultSet.getBoolean("users.admin")
        );
    }
}
