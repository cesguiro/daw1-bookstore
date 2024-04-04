package es.cesguiro.daw1bookstore.persistence.dao.impl.Memory.mapper;

import es.cesguiro.daw1bookstore.domain.model.OrderDetail;
import es.cesguiro.daw1bookstore.persistence.dao.BookDao;
import es.cesguiro.daw1bookstore.persistence.dao.impl.Memory.BookDaoMemory;
import es.cesguiro.daw1bookstore.persistence.dao.impl.Memory.data.record.OrderDetailRecord;

import java.util.ArrayList;
import java.util.List;

public class OrderDetailMapper {

    public static OrderDetail toOrderDetail(OrderDetailRecord orderDetailRecord) {
        BookDao bookDao = new BookDaoMemory();
        return new OrderDetail(
                orderDetailRecord.getId(),
                bookDao.findById(orderDetailRecord.getBookId()),
                orderDetailRecord.getQuantity(),
                orderDetailRecord.getPrice()
        );
    }

    public static List<OrderDetail> toOrderDetailList(List<OrderDetailRecord> orderDetailRecordList) {
        List<OrderDetail> orderDetailList = new ArrayList<>();
        for (OrderDetailRecord orderDetailRecord : orderDetailRecordList) {
            orderDetailList.add(toOrderDetail(orderDetailRecord));
        }
        return orderDetailList;
    }
}
