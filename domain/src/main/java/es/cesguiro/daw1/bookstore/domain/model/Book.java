package es.cesguiro.daw1.bookstore.domain.model;

import es.cesguiro.daw1.bookstore.domain.model.vo.LocaleString;
import es.cesguiro.daw1.bookstore.util.context.RequestContext;
import es.cesguiro.daw1.bookstore.util.context.RequestContextHolder;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;
import java.util.Locale;

public class Book {

    private String isbn;
    private LocaleString title;
    private LocaleString synopsis;
    private BigDecimal basePrice;
    private double discountPercentage;
    private BigDecimal finalPrice;
    private String cover;
    private LocalDate publicationDate;
    private Publisher publisher;
    private List<Author> authors;

    public Book(
            String isbn,
            LocaleString title,
            LocaleString synopsis,
            BigDecimal basePrice,
            double discountPercentage,
            String cover,
            LocalDate publicationDate
    ) {
        this.isbn = isbn;
        this.title = title;
        this.synopsis = synopsis;
        this.basePrice = basePrice;
        this.discountPercentage = discountPercentage;
        this.cover = cover;
        this.publicationDate = publicationDate;
        this.finalPrice = calculateFinalPrice();
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle(String language) {
        return title.getValue(language);
    }

    public String getTitle(){
        RequestContext context = RequestContextHolder.getRequestContext();
        Locale locale = context.getLocale();
        String lang = RequestContextHolder.getRequestContext().getLocale().getLanguage();
        return title.getValue(lang);
    }

    public void setTitle(LocaleString title) {
        this.title = title;
    }

    public String getSynopsis(String language) {
        return synopsis.getValue(language);
    }

    public String getSynopsis() {
        String lang = RequestContextHolder.getRequestContext().getLocale().getLanguage();
        return synopsis.getValue(lang);
    }

    public void setSynopsis(LocaleString synopsis) {
        this.synopsis = synopsis;
    }

    public BigDecimal getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(BigDecimal basePrice) {
        this.basePrice = basePrice;
    }

    public double getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(double discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    public BigDecimal getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(BigDecimal finalPrice) {
        this.finalPrice = finalPrice;
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

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    public LocalDate getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(LocalDate publicationDate) {
        this.publicationDate = publicationDate;
    }

    public BigDecimal calculateFinalPrice() {
        BigDecimal discountFactor = BigDecimal.ONE.subtract(BigDecimal.valueOf(discountPercentage).divide(BigDecimal.valueOf(100)));
        return basePrice.multiply(discountFactor).setScale(2, RoundingMode.HALF_UP);
    }
}
