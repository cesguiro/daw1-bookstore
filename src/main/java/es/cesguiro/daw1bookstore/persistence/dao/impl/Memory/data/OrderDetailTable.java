package es.cesguiro.daw1bookstore.persistence.dao.impl.Memory.data;

import es.cesguiro.daw1bookstore.persistence.dao.impl.Memory.data.record.OrderDetailsRecord;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class OrderDetailTable {

    List<OrderDetailsRecord> orderDetailsRecordList = new ArrayList<>();

    public OrderDetailTable(){
        orderDetailsRecordList.add(new OrderDetailsRecord(1, 3, 2, 2, new BigDecimal(12.30)));
        orderDetailsRecordList.add(new OrderDetailsRecord(2, 3, 4, 3, new BigDecimal(20.30)));
        orderDetailsRecordList.add(new OrderDetailsRecord(3, 4, 1, 1, new BigDecimal(13.20)));
        orderDetailsRecordList.add(new OrderDetailsRecord(4, 5, 3, 1, new BigDecimal(11.50)));
        orderDetailsRecordList.add(new OrderDetailsRecord(5, 5, 4, 2, new BigDecimal(20.30)));
        orderDetailsRecordList.add(new OrderDetailsRecord(6, 5, 5, 1, new BigDecimal(9.30)));
        orderDetailsRecordList.add(new OrderDetailsRecord(7, 6, 2, 4, new BigDecimal(12.30)));
        orderDetailsRecordList.add(new OrderDetailsRecord(8, 7, 1, 3, new BigDecimal(11.20)));
        orderDetailsRecordList.add(new OrderDetailsRecord(9, 7, 3, 2, new BigDecimal(52.60)));
        orderDetailsRecordList.add(new OrderDetailsRecord(10, 8, 1, 5, new BigDecimal(13.20)));
        orderDetailsRecordList.add(new OrderDetailsRecord(11, 8, 3, 2, new BigDecimal(11.50)));
        orderDetailsRecordList.add(new OrderDetailsRecord(12, 8, 4, 3, new BigDecimal(10.40)));
        orderDetailsRecordList.add(new OrderDetailsRecord(13, 8, 5, 1, new BigDecimal(9.30)));
    }

    public List<OrderDetailsRecord> selectByOrderId(int orderId) {
        List<OrderDetailsRecord> orderDetailRecordListByOrderId = new ArrayList<>();
        for (OrderDetailsRecord orderDetailsRecord : orderDetailsRecordList) {
            if (orderDetailsRecord.getOrderId() == orderId) {
                orderDetailRecordListByOrderId.add(orderDetailsRecord);
            }
        }
        return orderDetailRecordListByOrderId;
    }

    public OrderDetailsRecord selectById(Integer orderDetailId) {
        for (OrderDetailsRecord orderDetailsRecord : orderDetailsRecordList) {
            if (orderDetailsRecord.getId() == orderDetailId) {
                return orderDetailsRecord;
            }
        }
        return null;
    }
}
