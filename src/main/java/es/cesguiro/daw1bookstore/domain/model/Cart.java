package es.cesguiro.daw1bookstore.domain.model;

import es.cesguiro.daw1bookstore.common.exception.ResourceNotFoundException;

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

    public Cart(Integer id, User user, BigDecimal total, List<CartDetail> cartDetailList) {
        this.id = id;
        this.user = user;
        setTotal(total);
        this.cartDetailList = cartDetailList;
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
        total = new BigDecimal(0);
        this.cartDetailList = cartDetailList;
        for (CartDetail cartDetail : cartDetailList) {
            total = total.add(cartDetail.getTotal());
        }
    }

    public void addCartDetail(CartDetail cartDetail) {
        for (CartDetail cd : cartDetailList) {
            if (cd.getBook().getId().equals(cartDetail.getBook().getId())) {
                cd.setQuantity(cd.getQuantity() + cartDetail.getQuantity());
                total = total.add(cartDetail.getTotal());
                return;
            }
        }
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

    /*public void removeCartDetail(Book book) {
        for (CartDetail cartDetail : cartDetailList) {
            if (cartDetail.getBook().getId().equals(book.getId())) {
                total = total.subtract(cartDetail.getTotal());
                cartDetailList.remove(cartDetail);
                return;
            }
        }
        throw new ResourceNotFoundException("Book not found in cart");
    }*/

    public void removeCartDetail(int cartDetailId) {
        for (CartDetail cartDetail : cartDetailList) {
            if (cartDetail.getId().equals(cartDetailId)) {
                total = total.subtract(cartDetail.getTotal());
                cartDetailList.remove(cartDetail);
                return;
            }
        }
        throw new ResourceNotFoundException("Book not found in cart");
    }
}
