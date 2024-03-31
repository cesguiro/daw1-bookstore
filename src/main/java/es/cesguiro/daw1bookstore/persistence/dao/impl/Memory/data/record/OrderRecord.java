package es.cesguiro.daw1bookstore.persistence.dao.impl.Memory.data.record;

import java.time.LocalDate;

public class OrderRecord {

    private int id;
    private int userId;
    private LocalDate orderDate;
    private LocalDate deliveryDate;
    private double total;

    /*
        * 0: pending (cart)
        * 1: delivered
        * 2: in process
        * 3: sent
        * 4: received
     */
    private int status;

    public OrderRecord(int id, int userId, LocalDate orderDate, LocalDate deliveryDate, double total, int status) {
        this.id = id;
        this.userId = userId;
        this.orderDate = orderDate;
        this.deliveryDate = deliveryDate;
        this.total = total;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public LocalDate getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(LocalDate deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
