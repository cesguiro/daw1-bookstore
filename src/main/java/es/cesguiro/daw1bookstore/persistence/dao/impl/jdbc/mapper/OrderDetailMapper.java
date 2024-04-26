package es.cesguiro.daw1bookstore.persistence.dao.impl.jdbc.mapper;

import es.cesguiro.daw1bookstore.domain.model.Book;
import es.cesguiro.daw1bookstore.domain.model.CartDetail;
import es.cesguiro.daw1bookstore.domain.model.OrderDetail;
import es.cesguiro.daw1bookstore.persistence.dao.BookDao;
import es.cesguiro.daw1bookstore.persistence.dao.impl.jdbc.BookDaoJdbc;
import org.springframework.context.i18n.LocaleContextHolder;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class OrderDetailMapper {

    public static OrderDetail toOrderDetail(ResultSet resultSet) throws SQLException {
        if(resultSet == null) {
            return null;
        }
        OrderDetail orderDetail = new OrderDetail(
                resultSet.getInt("id"),
                getBook(resultSet),
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

    public static CartDetail toCartDetail(ResultSet resultSet) throws SQLException {
        if(resultSet == null) {
            return null;
        }
        CartDetail cartDetail = new CartDetail(
                resultSet.getInt("id"),
                getBook(resultSet),
                resultSet.getInt("quantity"),
                resultSet.getBigDecimal("price")
        );
        return cartDetail;
    }

    public static List<CartDetail> toCartDetailList(ResultSet resultSet) throws SQLException {
        List<CartDetail> cartDetailList = new ArrayList<>();
        while (resultSet.next()) {
            cartDetailList.add(toCartDetail(resultSet));
        }
        return cartDetailList;
    }

    private static Book getBook(ResultSet resultSet) throws SQLException {
        if (resultSet == null) {
            return null;
        }
        return  new Book(
                resultSet.getInt("books.id"),
                resultSet.getString("isbn"),
                resultSet.getString("title"),
                resultSet.getString("synopsis"),
                resultSet.getBigDecimal("books.price"),
                resultSet.getString("cover")
        );
    }
}
