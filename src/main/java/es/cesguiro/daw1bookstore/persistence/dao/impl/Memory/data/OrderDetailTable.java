package es.cesguiro.daw1bookstore.persistence.dao.impl.Memory.data;

import es.cesguiro.daw1bookstore.persistence.dao.impl.Memory.data.record.OrderDetailRecord;
import es.cesguiro.daw1bookstore.persistence.dao.impl.Memory.data.record.OrderRecord;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class OrderDetailTable {

    List<OrderDetailRecord> orderDetailRecordList = new ArrayList<>();

    public OrderDetailTable(){
        orderDetailRecordList.add(new OrderDetailRecord(1, 3, 2, 2, new BigDecimal(12.30)));
        orderDetailRecordList.add(new OrderDetailRecord(2, 3, 4, 3, new BigDecimal(20.30)));
        orderDetailRecordList.add(new OrderDetailRecord(3, 4, 1, 1, new BigDecimal(13.20)));
        orderDetailRecordList.add(new OrderDetailRecord(4, 5, 3, 1, new BigDecimal(11.50)));
        orderDetailRecordList.add(new OrderDetailRecord(5, 5, 4, 2, new BigDecimal(20.30)));
        orderDetailRecordList.add(new OrderDetailRecord(6, 5, 5, 1, new BigDecimal(9.30)));
        orderDetailRecordList.add(new OrderDetailRecord(7, 6, 2, 4, new BigDecimal(12.30)));
        orderDetailRecordList.add(new OrderDetailRecord(8, 7, 1, 3, new BigDecimal(11.20)));
        orderDetailRecordList.add(new OrderDetailRecord(9, 7, 3, 2, new BigDecimal(52.60)));
        orderDetailRecordList.add(new OrderDetailRecord(10, 8, 1, 5, new BigDecimal(13.20)));
        orderDetailRecordList.add(new OrderDetailRecord(11, 8, 3, 2, new BigDecimal(11.50)));
        orderDetailRecordList.add(new OrderDetailRecord(12, 8, 4, 3, new BigDecimal(10.40)));
        orderDetailRecordList.add(new OrderDetailRecord(13, 8, 5, 1, new BigDecimal(9.30)));
    }

    public List<OrderDetailRecord> selectByOrderId(int orderId) {
        List<OrderDetailRecord> orderDetailRecordListByOrderId = new ArrayList<>();
        for (OrderDetailRecord orderDetailRecord : orderDetailRecordList) {
            if (orderDetailRecord.getOrderId() == orderId) {
                orderDetailRecordListByOrderId.add(orderDetailRecord);
            }
        }
        return orderDetailRecordListByOrderId;
    }

    public OrderDetailRecord selectById(Integer orderDetailId) {
        for (OrderDetailRecord orderDetailRecord : orderDetailRecordList) {
            if (orderDetailRecord.getId() == orderDetailId) {
                return orderDetailRecord;
            }
        }
        return null;
    }
}
