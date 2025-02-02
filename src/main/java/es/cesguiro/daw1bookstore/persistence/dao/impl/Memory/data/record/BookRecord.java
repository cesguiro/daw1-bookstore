package es.cesguiro.daw1bookstore.persistence.dao.impl.Memory.data.record;

import java.math.BigDecimal;

public class BookRecord {
    private Integer id;
    private String isbn;
    private String title;
    private String titleEn;
    private String synopsis;
    private String synopsisEn;
    private BigDecimal price;
    private String cover;
    private int publisherId;

    public BookRecord(Integer id, String isbn, String title, String titleEn, String synopsis, String synopsisEn, BigDecimal price, String cover, int publisherId) {
        this.id = id;
        this.isbn = isbn;
        this.title = title;
        this.titleEn = titleEn;
        this.synopsis = synopsis;
        this.synopsisEn = synopsisEn;
        this.price = price;
        this.cover = cover;
        this.publisherId = publisherId;
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
        this.price = price;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public int getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(int publisherId) {
        this.publisherId = publisherId;
    }

    public String getTitleEn() {
        return titleEn;
    }

    public void setTitleEn(String titleEn) {
        this.titleEn = titleEn;
    }

    public String getSynopsisEn() {
        return synopsisEn;
    }

    public void setSynopsisEn(String synopsisEn) {
        this.synopsisEn = synopsisEn;
    }
}

