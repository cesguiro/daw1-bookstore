package es.cesguiro.daw1bookstore.persistence.dao.impl.jdbc.mapper;

import es.cesguiro.daw1bookstore.domain.model.Book;
import es.cesguiro.daw1bookstore.domain.model.OrderDetail;
import es.cesguiro.daw1bookstore.persistence.dao.BookDao;
import es.cesguiro.daw1bookstore.persistence.dao.impl.jdbc.BookDaoJdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDetailMapper {

    public static OrderDetail toOrderDetail(ResultSet resultSet) throws SQLException {
        BookDao bookDao = new BookDaoJdbc();
        Book book =  bookDao.findById(resultSet.getInt("book_id"));
        OrderDetail orderDetail = new OrderDetail(
                resultSet.getInt("id"),
                book,
                resultSet.getInt("quantity"),
                resultSet.getBigDecimal("price")
        );
        return orderDetail;
    }

    public static List<OrderDetail> toOrderDetailList(ResultSet resultSet) throws SQLException {
        List<OrderDetail> orderDetailList = new ArrayList<>();
        while (resultSet.next()) {
            orderDetailList.add(toOrderDetail(resultSet));
        }
        return orderDetailList;
    }
}
