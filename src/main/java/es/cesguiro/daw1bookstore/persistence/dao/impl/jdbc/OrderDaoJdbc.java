package es.cesguiro.daw1bookstore.persistence.dao.impl.jdbc;

import es.cesguiro.daw1bookstore.common.exception.QueryBuilderSQLException;
import es.cesguiro.daw1bookstore.domain.model.Order;
import es.cesguiro.daw1bookstore.persistence.dao.OrderDao;
import es.cesguiro.daw1bookstore.persistence.dao.impl.jdbc.mapper.OrderMapper;
import es.cesguiro.daw1bookstore.persistence.dao.impl.jdbc.queryBuilder.DB;

import java.sql.ResultSet;
import java.util.List;

public class OrderDaoJdbc implements OrderDao {

    @Override
    public List<Order> findOrderByUserId(Integer userId) {
        try {
            ResultSet resultSet = DB
                    .table("orders")
                    .join("users", "orders.user_id", "users.id")
                    .where("user_id", "=", userId)
                    .andWhere("status", "!=", 0)
                    .get();
            return OrderMapper.toOrderList(resultSet);
        } catch (Exception e) {
            throw new QueryBuilderSQLException(e.getMessage());
        }
    }

    @Override
    public Order findById(Integer id) {
        try {
            ResultSet resultSet = DB
                    .table("orders")
                    .join("users", "orders.user_id", "users.id")
                    .where("orders.id", "=", id)
                    .get();
            if(!resultSet.next()) {
                return null;
            }
            return OrderMapper.toOrderWithOrderDetailList(resultSet);
        } catch (Exception e) {
            throw new QueryBuilderSQLException(e.getMessage());
        }
    }
}
