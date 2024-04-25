package es.cesguiro.daw1bookstore.domain.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Objects;

public class Cart {

    private Integer id;
    private User user;
    private BigDecimal total;
    List<CartDetail> cartDetailList;

    public Cart(Integer id, User user, BigDecimal total) {
        this.id = id;
        this.user = user;
        setTotal(total);
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

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        if (total == null) {
            total = new BigDecimal(0);
        }
        this.total = total.setScale(2, RoundingMode.HALF_UP);
    }

    public List<CartDetail> getCartDetailList() {
        return cartDetailList;
    }

    public void setCartDetailList(List<CartDetail> cartDetailList) {
        this.cartDetailList = cartDetailList;
        for (CartDetail cartDetail : cartDetailList) {
            total = total.add(cartDetail.getTotal());
        }
    }

    public void addCartDetail(CartDetail cartDetail) {
        cartDetailList.add(cartDetail);
        total = total.add(cartDetail.getTotal());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cart cart = (Cart) o;
        return Objects.equals(id, cart.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
