package es.cesguiro.daw1bookstore.common.container;

import es.cesguiro.daw1bookstore.persistence.dao.OrderDetailDao;
import es.cesguiro.daw1bookstore.persistence.dao.impl.jdbc.OrderDetailDaoJdbc;

public class OrderDetailIoc {

    private static OrderDetailDao orderDetailDao;

    public static OrderDetailDao getOrderDetailDao() {
        if(orderDetailDao == null) {
            orderDetailDao = new OrderDetailDaoJdbc();
        }
        return orderDetailDao;
    }

    public static void setOrderDetailDao(OrderDetailDao orderDetailDao) {
        OrderDetailIoc.orderDetailDao = orderDetailDao;
    }

    public static void reset() {
        orderDetailDao = null;
    }
}
