package es.cesguiro.daw1bookstore.persistence.dao.impl.jdbc;

import es.cesguiro.daw1bookstore.common.exception.QueryBuilderSQLException;
import es.cesguiro.daw1bookstore.domain.model.OrderDetail;
import es.cesguiro.daw1bookstore.persistence.dao.OrderDetailDao;
import es.cesguiro.daw1bookstore.persistence.dao.impl.jdbc.mapper.OrderDetailMapper;
import es.cesguiro.daw1bookstore.persistence.dao.impl.jdbc.queryBuilder.DB;

import java.sql.ResultSet;
import java.util.List;

public class OrderDetailDaoJdbc implements OrderDetailDao {

    @Override
    public List<OrderDetail> findByOrderId(Integer orderId) {
        try {
            ResultSet resultSet = DB
                    .table("order_details")
                    .join("orders", "order_details.order_id", "orders.id")
                    .join("books", "order_details.book_id", "books.id")
                    .where("orders.id", "=", orderId)
                    .get();
            return OrderDetailMapper.toOrderDetailList(resultSet);
        } catch (Exception e) {
            throw new QueryBuilderSQLException(e.getMessage());
        }
    }
}
