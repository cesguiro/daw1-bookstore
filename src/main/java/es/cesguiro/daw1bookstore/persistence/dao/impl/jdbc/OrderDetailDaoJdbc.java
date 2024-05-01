package es.cesguiro.daw1bookstore.persistence.dao.impl.jdbc;

import es.cesguiro.daw1bookstore.common.exception.QueryBuilderSQLException;
import es.cesguiro.daw1bookstore.domain.model.CartDetail;
import es.cesguiro.daw1bookstore.domain.model.OrderDetail;
import es.cesguiro.daw1bookstore.persistence.dao.OrderDetailDao;
import es.cesguiro.daw1bookstore.persistence.dao.impl.jdbc.mapper.OrderDetailMapper;
import es.cesguiro.daw1bookstore.persistence.dao.impl.jdbc.queryBuilder.DB;
import org.springframework.context.i18n.LocaleContextHolder;

import java.sql.ResultSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class OrderDetailDaoJdbc implements OrderDetailDao {

    @Override
    public List<OrderDetail> findByOrderId(Integer orderId) {
        try {
            Locale currentLocale = LocaleContextHolder.getLocale();
            String language = currentLocale.getLanguage();
            ResultSet resultSet = DB
                    .table("order_details")
                    .select(
                            "order_details.id",
                            "order_details.quantity",
                            "order_details.price",
                            "books.id",
                            "books.isbn",
                            "books.title_" + language + " AS title",
                            "books.synopsis_" + language + " AS synopsis",
                            "books.price",
                            "books.cover"
                    )
                    .join("orders", "order_details.order_id", "orders.id")
                    .join("books", "order_details.book_id", "books.id")
                    .where("orders.id", "=", orderId)
                    .get();
            return OrderDetailMapper.toOrderDetailList(resultSet);
        } catch (Exception e) {
            throw new QueryBuilderSQLException(e.getMessage());
        }
    }

    @Override
    public List<CartDetail> findCartDetailListByCartId(Integer cartId) {
        Locale currentLocale = LocaleContextHolder.getLocale();
        String language = currentLocale.getLanguage();
        try {
            ResultSet resultSet = DB
                    .table("order_details")
                    .select(
                            "order_details.id",
                            "order_details.quantity",
                            "order_details.price",
                            "books.id",
                            "books.isbn",
                            "books.title_" + language + " AS title",
                            "books.synopsis_" + language + " AS synopsis",
                            "books.price",
                            "books.cover"
                    )
                    .join("orders", "order_details.order_id", "orders.id")
                    .join("books", "order_details.book_id", "books.id")
                    .where("orders.id", "=", cartId)
                    .get();
            return OrderDetailMapper.toCartDetailList(resultSet);
        } catch (Exception e) {
            throw new QueryBuilderSQLException(e.getMessage());
        }
    }

    @Override
    public void insertCartDetailIntoCart(Integer cartId, CartDetail cartDetail) {
        try {
            DB.table("order_details")
                    .insert(Map.of(
                                    "order_id", cartId,
                                    "book_id", cartDetail.getBook().getId(),
                                    "quantity", cartDetail.getQuantity(),
                                    "price", cartDetail.getPrice()
                            )
                    );
        } catch (Exception e) {
            throw new QueryBuilderSQLException(e.getMessage());
        }

    }

    @Override
    public void deleteCartDetailListByCartId(Integer cartId) {
        // Delete the cart details
        DB.table("order_details")
                .where("order_id", "=", cartId)
                .delete();
    }
}
