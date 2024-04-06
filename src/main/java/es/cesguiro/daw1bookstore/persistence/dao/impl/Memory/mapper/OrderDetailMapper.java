package es.cesguiro.daw1bookstore.persistence.dao.impl.Memory.mapper;

import es.cesguiro.daw1bookstore.domain.model.OrderDetail;
import es.cesguiro.daw1bookstore.persistence.dao.BookDao;
import es.cesguiro.daw1bookstore.persistence.dao.impl.Memory.BookDaoMemory;
import es.cesguiro.daw1bookstore.persistence.dao.impl.Memory.data.record.OrderDetailsRecord;

import java.util.ArrayList;
import java.util.List;

public class OrderDetailMapper {

    public static OrderDetail toOrderDetail(OrderDetailsRecord orderDetailsRecord) {
        BookDao bookDao = new BookDaoMemory();
        return new OrderDetail(
                orderDetailsRecord.getId(),
                bookDao.findById(orderDetailsRecord.getBookId()),
                orderDetailsRecord.getQuantity(),
                orderDetailsRecord.getPrice()
        );
    }

    public static List<OrderDetail> toOrderDetailList(List<OrderDetailsRecord> orderDetailsRecordList) {
        List<OrderDetail> orderDetailList = new ArrayList<>();
        for (OrderDetailsRecord orderDetailsRecord : orderDetailsRecordList) {
            orderDetailList.add(toOrderDetail(orderDetailsRecord));
        }
        return orderDetailList;
    }
}
