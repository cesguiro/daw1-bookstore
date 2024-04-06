package es.cesguiro.daw1bookstore.common.container;

import es.cesguiro.daw1bookstore.domain.service.OrderService;
import es.cesguiro.daw1bookstore.domain.service.impl.OrderServiceImpl;
import es.cesguiro.daw1bookstore.persistence.dao.OrderDao;
import es.cesguiro.daw1bookstore.persistence.dao.impl.Memory.OrderDaoMemory;
import es.cesguiro.daw1bookstore.persistence.dao.impl.jdbc.OrderDaoJdbc;
import es.cesguiro.daw1bookstore.persistence.repository.OrderRepository;
import es.cesguiro.daw1bookstore.persistence.repository.impl.OrderRepositoryImpl;


public class OrderIoc {

    private static OrderService orderService;
    private static OrderRepository orderRepository;

    private static OrderDao orderDao;

    public static OrderService getOrderService() {
        if (orderService == null) {
            orderService = new OrderServiceImpl(getOrderRespository());
        }
        return orderService;
    }

    public static OrderRepository getOrderRespository() {
        if (orderRepository == null) {
            orderRepository = new OrderRepositoryImpl(getOrderDao());
        }
        return orderRepository;
    }

    public static OrderDao getOrderDao() {
        if (orderDao == null) {
            //orderDao = new OrderDaoMemory();
            orderDao = new OrderDaoJdbc();
        }
        return orderDao;
    }

    public static void setOrderService(OrderService orderService) {
        OrderIoc.orderService = orderService;
    }

    public static void setOrderRespository(OrderRepository orderRepository) {
        OrderIoc.orderRepository = orderRepository;
    }

    public static void setOrderDao(OrderDao orderDao) {
        OrderIoc.orderDao = orderDao;
    }

    public static void reset() {
        orderService = null;
        orderRepository = null;
        orderDao = null;
    }
}
