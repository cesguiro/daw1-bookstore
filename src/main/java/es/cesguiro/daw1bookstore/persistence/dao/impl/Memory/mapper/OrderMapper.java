package es.cesguiro.daw1bookstore.persistence.dao.impl.Memory.mapper;

import es.cesguiro.daw1bookstore.domain.model.Order;
import es.cesguiro.daw1bookstore.domain.model.OrderDetail;
import es.cesguiro.daw1bookstore.persistence.dao.OrderDetailDao;
import es.cesguiro.daw1bookstore.persistence.dao.UserDao;
import es.cesguiro.daw1bookstore.persistence.dao.impl.Memory.OrderDetailDaoMemory;
import es.cesguiro.daw1bookstore.persistence.dao.impl.Memory.UserDaoMemory;
import es.cesguiro.daw1bookstore.persistence.dao.impl.Memory.data.record.OrderDetailRecord;
import es.cesguiro.daw1bookstore.persistence.dao.impl.Memory.data.record.OrderRecord;

import java.util.ArrayList;
import java.util.List;

public class OrderMapper {

    public static Order toOrder(OrderRecord orderRecord) {
        Order order = new Order(
                orderRecord.getId(),
                orderRecord.getOrderDate(),
                orderRecord.getDeliveryDate(),
                orderRecord.getTotal(),
                orderRecord.getStatus()
        );
        //Añadimos el usuario al pedido
        UserDao userDao = new UserDaoMemory();
        order.setUser(userDao.findById(orderRecord.getUserId()));
        return order;
    }

    public static Order toOrderWithOrderDetailList(OrderRecord orderRecord) {
        Order order = toOrder(orderRecord);
        OrderDetailDao orderDetailDao = new OrderDetailDaoMemory();
        order.setOrderDetailList(orderDetailDao.findByOrderId(orderRecord.getId()));
        return order;
    }

    public static List<Order> toOrderList(List<OrderRecord> orderRecordList) {
        List<Order> orderList = new ArrayList<>();
        for (OrderRecord orderRecord : orderRecordList) {
            orderList.add(toOrder(orderRecord));
        }
        return orderList;
    }

}
