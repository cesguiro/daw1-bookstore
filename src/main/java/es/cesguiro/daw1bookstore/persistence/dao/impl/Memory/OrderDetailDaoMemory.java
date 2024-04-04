package es.cesguiro.daw1bookstore.persistence.dao.impl.Memory;

import es.cesguiro.daw1bookstore.common.container.BookIoc;
import es.cesguiro.daw1bookstore.domain.model.Book;
import es.cesguiro.daw1bookstore.domain.model.OrderDetail;
import es.cesguiro.daw1bookstore.persistence.dao.BookDao;
import es.cesguiro.daw1bookstore.persistence.dao.OrderDetailDao;
import es.cesguiro.daw1bookstore.persistence.dao.impl.Memory.data.OrderDetailTable;
import es.cesguiro.daw1bookstore.persistence.dao.impl.Memory.data.record.OrderDetailRecord;
import es.cesguiro.daw1bookstore.persistence.dao.impl.Memory.mapper.BookMapper;
import es.cesguiro.daw1bookstore.persistence.dao.impl.Memory.mapper.OrderDetailMapper;

import java.util.List;

public class OrderDetailDaoMemory implements OrderDetailDao {

    OrderDetailTable orderDetailTable = new OrderDetailTable();

    @Override
    public List<OrderDetail> findByOrderId(Integer orderId) {
        return OrderDetailMapper.toOrderDetailList(orderDetailTable.selectByOrderId(orderId));
    }
}
