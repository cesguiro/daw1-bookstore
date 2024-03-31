package es.cesguiro.daw1bookstore.persistence.dao.impl.Memory;

import es.cesguiro.daw1bookstore.domain.model.Order;
import es.cesguiro.daw1bookstore.persistence.dao.OrderDao;
import es.cesguiro.daw1bookstore.persistence.dao.impl.Memory.data.OrderTable;
import es.cesguiro.daw1bookstore.persistence.dao.impl.Memory.data.record.OrderRecord;
import es.cesguiro.daw1bookstore.persistence.dao.impl.Memory.mapper.OrderMapper;

import java.util.List;

public class OrderDaoMemory implements OrderDao {

    private OrderTable orderTable = new OrderTable();


    @Override
    public List<Order> findByUserId(Integer userId) {
        List<OrderRecord> orderRecordList = orderTable.selectByUserId(userId);
        return OrderMapper.toOrderList(orderRecordList);
    }
}
