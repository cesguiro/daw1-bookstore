package es.cesguiro.daw1bookstore.persistence.dao.impl.Memory.mapper;

import es.cesguiro.daw1bookstore.domain.model.Order;
import es.cesguiro.daw1bookstore.persistence.dao.impl.Memory.data.record.OrderRecord;

import java.util.ArrayList;
import java.util.List;

public class OrderMapper {

    public static Order toOrder(OrderRecord orderRecord) {
        return new Order(
                orderRecord.getId(),
                orderRecord.getOrderDate(),
                orderRecord.getDeliveryDate(),
                orderRecord.getTotal(),
                orderRecord.getStatus()
        );
    }

    public static List<Order> toOrderList(List<OrderRecord> orderRecordList) {
        List<Order> orderList = new ArrayList<>();
        for (OrderRecord orderRecord : orderRecordList) {
            orderList.add(toOrder(orderRecord));
        }
        return orderList;
    }

}
