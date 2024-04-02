package es.cesguiro.daw1bookstore.domain.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Objects;


public class Order {

    private Integer id;
    private User user;
    private LocalDate orderDate;
    private LocalDate deliveryDate;
    private BigDecimal total;

    /*
     * 1: delivered
     * 2: in process
     * 3: sent
     * 4: received
     */
    private int status;

    public Order(Integer id, User user, LocalDate orderDate, LocalDate deliveryDate, BigDecimal total, int status) {
        this.id = id;
        this.user = user;
        this.orderDate = orderDate;
        this.deliveryDate = deliveryDate;
        setTotal(total);
        this.status = status;
    }

    public Order(Integer id, LocalDate orderDate, LocalDate deliveryDate, BigDecimal total, int status) {
        this.id = id;
        this.orderDate = orderDate;
        this.deliveryDate = deliveryDate;
        setTotal(total);
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        if (total == null) {
            total = new BigDecimal(0);
        }
        this.total = total.setScale(2, RoundingMode.HALF_UP);
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
