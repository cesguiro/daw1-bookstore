package es.cesguiro.daw1bookstore.persistence.repository.mapper;

import es.cesguiro.daw1bookstore.domain.model.Book;
import es.cesguiro.daw1bookstore.persistence.dao.entity.BookEntity;

import java.util.ArrayList;
import java.util.List;

public class BookMapper {

    public static Book toBook(BookEntity bookEntity) {
        if(bookEntity == null) {
            return null;
        }
        Book book = new Book(
                bookEntity.getId(),
                bookEntity.getIsbn(),
                bookEntity.getTitle(),
                bookEntity.getSynopsis(),
                bookEntity.getPrice(),
                bookEntity.getCover()
        );
        if(bookEntity.getPublisherEntity() != null) {
            book.setPublisher(PublisherMapper.toPublisher(bookEntity.getPublisherEntity()));
        }
        if(bookEntity.getAuthorEntityList() != null) {
            book.setAuthorList(AuthorMapper.toAuthorList(bookEntity.getAuthorEntityList()));
        }
        return book;
    }

    public static List<Book> toBookList(List<BookEntity> bookEntityList) {
        if(bookEntityList == null) {
            return null;
        }
        List<Book> bookList = new ArrayList<>();
        for(BookEntity bookEntity : bookEntityList) {
            bookList.add(toBook(bookEntity));
        }
        return bookList;
    }
}
