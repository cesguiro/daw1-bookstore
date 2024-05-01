package es.cesguiro.daw1bookstore.persistence.dao.impl.jdbc;

import es.cesguiro.daw1bookstore.common.UserUtil;
import es.cesguiro.daw1bookstore.common.exception.QueryBuilderSQLException;
import es.cesguiro.daw1bookstore.domain.model.Cart;
import es.cesguiro.daw1bookstore.domain.model.Order;
import es.cesguiro.daw1bookstore.domain.model.User;
import es.cesguiro.daw1bookstore.persistence.dao.OrderDao;
import es.cesguiro.daw1bookstore.persistence.dao.impl.jdbc.mapper.OrderMapper;
import es.cesguiro.daw1bookstore.persistence.dao.impl.jdbc.queryBuilder.DB;

import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

public class OrderDaoJdbc implements OrderDao {

    @Override
    public List<Order> findByUserId(Integer userId) {
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
            return OrderMapper.toOrder(resultSet);
        } catch (Exception e) {
            throw new QueryBuilderSQLException(e.getMessage());
        }
    }

    @Override
    public Cart findCartByUserId(Integer userId) {
        try {
            ResultSet resultSet = DB
                    .table("orders")
                    .join("users", "orders.user_id", "users.id")
                    .andWhere("status", "=", 0)
                    .where("user_id", "=", userId)
                    .get();
            if(!resultSet.next()) {
                return null;
            }
            return OrderMapper.toCart(resultSet);
        } catch (Exception e) {
            throw new QueryBuilderSQLException(e.getMessage());
        }
    }

    @Override
    public void update(Cart cart) {
        try {
            DB.table("orders")
                    .where("id", "=", cart.getId())
                    .update(Map.of(
                            "total", cart.getTotal(),
                            "status", 0
                    ));
        } catch (Exception e) {
            throw new QueryBuilderSQLException(e.getMessage());
        }
    }

    @Override
    public List<Order> findAll() {
        try {
            ResultSet resultSet = DB
                    .table("orders")
                    .get();
            return OrderMapper.toOrderList(resultSet);
        } catch (Exception e) {
            throw new QueryBuilderSQLException(e.getMessage());
        }
    }

    @Override
    public Order findByIdAndUserId(Integer id, Integer userId) {
        try {
            ResultSet resultSet = DB
                    .table("orders")
                    .join("users", "orders.user_id", "users.id")
                    .where("orders.id", "=", id)
                    .andWhere("user_id", "=", userId)
                    .get();
            if(!resultSet.next()) {
                return null;
            }
            return OrderMapper.toOrder(resultSet);
        } catch (Exception e) {
            throw new QueryBuilderSQLException(e.getMessage());
        }    }
}
