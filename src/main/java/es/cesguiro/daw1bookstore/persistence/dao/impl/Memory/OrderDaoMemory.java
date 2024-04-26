package es.cesguiro.daw1bookstore.persistence.dao.impl.Memory;

import es.cesguiro.daw1bookstore.domain.model.Cart;
import es.cesguiro.daw1bookstore.domain.model.Order;
import es.cesguiro.daw1bookstore.persistence.dao.OrderDao;
import es.cesguiro.daw1bookstore.persistence.dao.impl.Memory.data.OrderTable;
import es.cesguiro.daw1bookstore.persistence.dao.impl.Memory.data.record.OrderRecord;
import es.cesguiro.daw1bookstore.persistence.dao.impl.Memory.mapper.OrderMapper;

import java.util.List;

public class OrderDaoMemory implements OrderDao {

    private OrderTable orderTable = new OrderTable();


    @Override
    public List<Order> findOrderByUserId(Integer userId) {
        List<OrderRecord> orderRecordList = orderTable.selectOrderByUserId(userId);
        return OrderMapper.toOrderList(orderRecordList);
    }

    @Override
    public Order findById(Integer id) {
        OrderRecord orderRecord = orderTable.selectById(id);
        if (orderRecord == null) {
            return null;
        }
        return OrderMapper.toOrderWithOrderDetailList(orderRecord);
    }

    @Override
    public Cart findCartByUserId(Integer userId) {
        return null;
    }
}
