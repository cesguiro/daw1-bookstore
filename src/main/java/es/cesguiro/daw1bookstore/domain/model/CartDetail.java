package es.cesguiro.daw1bookstore.domain.model;

import java.math.BigDecimal;
import java.util.Objects;

public class CartDetail {

    private Integer id;
    private Book book;
    private Integer quantity;
    private BigDecimal price;

    public CartDetail(Integer id, Book book, Integer quantity, BigDecimal price) {
        this.id = id;
        this.book = book;
        this.quantity = quantity;
        setPrice(price);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price.setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    public BigDecimal getTotal() {
        return price.multiply(new BigDecimal(quantity));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartDetail that = (CartDetail) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

}
