package es.cesguiro.daw1bookstore.persistence.dao.impl.Memory;

import es.cesguiro.daw1bookstore.domain.model.CartDetail;
import es.cesguiro.daw1bookstore.domain.model.OrderDetail;
import es.cesguiro.daw1bookstore.persistence.dao.OrderDetailDao;
import es.cesguiro.daw1bookstore.persistence.dao.impl.Memory.data.OrderDetailTable;
import es.cesguiro.daw1bookstore.persistence.dao.impl.Memory.mapper.OrderDetailMapper;

import java.util.List;

public class OrderDetailDaoMemory implements OrderDetailDao {

    OrderDetailTable orderDetailTable = new OrderDetailTable();

    @Override
    public List<OrderDetail> findByOrderId(Integer orderId) {
        return OrderDetailMapper.toOrderDetailList(orderDetailTable.selectByOrderId(orderId));
    }

    @Override
    public List<CartDetail> findCartDetailListByCartId(Integer cartId) {
        return List.of();
    }

    @Override
    public void insertCartDetailIntoCart(Integer cartId, CartDetail cartDetail) {

    }

    @Override
    public void deleteCartDetailListByCartId(Integer cartId) {

    }
}
