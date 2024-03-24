package es.cesguiro.daw1bookstore.domain.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class Book {

    private Integer id;
    private String isbn;
    private String title;
    private String synopsis;
    private BigDecimal price;
    private String cover;
    private Publisher publisher;
    List<Author> authorList;

    public Book(Integer id, String isbn, String title, String synopsis, BigDecimal price, String cover) {
        this.id = id;
        this.isbn = isbn;
        this.title = title;
        this.synopsis = synopsis;
        this.price = price.setScale(2, RoundingMode.HALF_UP);
        this.cover = cover;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price.setScale(2, RoundingMode.HALF_UP);
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public List<Author> getAuthorList() {
        return authorList;
    }

    public void setAuthorList(List<Author> authorList) {
        this.authorList = authorList;
    }

    public void addAuthor(Author author) {
        if (authorList == null) {
            authorList = new ArrayList<>();
        }
        authorList.add(author);
    }
}
