package es.cesguiro.daw1bookstore.common.container;

import es.cesguiro.daw1bookstore.domain.service.OrderService;
import es.cesguiro.daw1bookstore.domain.service.impl.OrderServiceImpl;
import es.cesguiro.daw1bookstore.persistence.dao.OrderDao;
import es.cesguiro.daw1bookstore.persistence.dao.impl.Memory.OrderDaoMemory;
import es.cesguiro.daw1bookstore.persistence.repository.OrderRespository;
import es.cesguiro.daw1bookstore.persistence.repository.impl.OrderRespositoryImpl;

public class OrderIoc {

    private static OrderService orderService;
    private static OrderRespository orderRespository;
    private static OrderDao orderDao;

    public static OrderService getOrderService() {
        if (orderService == null) {
            orderService = new OrderServiceImpl(getOrderRespository());
        }
        return orderService;
    }

    private static OrderRespository getOrderRespository() {
        if (orderRespository == null) {
            orderRespository = new OrderRespositoryImpl(getOrderDao());
        }
        return orderRespository;
    }

    private static OrderDao getOrderDao() {
        if (orderDao == null) {
            orderDao = new OrderDaoMemory();
        }
        return orderDao;
    }
}
