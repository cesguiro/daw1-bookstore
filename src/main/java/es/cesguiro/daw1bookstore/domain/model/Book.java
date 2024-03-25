package es.cesguiro.daw1bookstore.domain.model;

import es.cesguiro.daw1bookstore.common.container.AuthorIoc;
import es.cesguiro.daw1bookstore.common.container.PublisherIoc;
import es.cesguiro.daw1bookstore.persistence.dao.PublisherDao;
import es.cesguiro.daw1bookstore.persistence.repository.AuthorRepository;
import es.cesguiro.daw1bookstore.persistence.repository.PublisherRepository;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
        this.setPrice(price);
        this.cover = cover;
    }

    public Book() {

    }

    public Book(Integer id, String isbn, String title, String synopsis, BigDecimal price, String cover, Publisher publisher, List<Author> authorList) {
        this.id = id;
        this.isbn = isbn;
        this.title = title;
        this.synopsis = synopsis;
        this.setPrice(price);
        this.cover = cover;
        this.publisher = publisher;
        this.authorList = authorList;
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
        if (price == null) {
            price = new BigDecimal(0);
        }
        this.price = price.setScale(2, RoundingMode.HALF_UP);
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    //lazy loading
    public Publisher getPublisher() {
        if(publisher == null) {
            PublisherRepository publisherRepository = PublisherIoc.getPublisherRepository();
            publisher = publisherRepository.findByBookId(id);
        }
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    //lazy loading
    public List<Author> getAuthorList() {
        if(authorList == null) {
            AuthorRepository authorRepository = AuthorIoc.getAuthorRepository();
            authorList = authorRepository.findByBookId(id);
        }
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(isbn, book.isbn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isbn);
    }
}
