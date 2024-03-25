package es.cesguiro.daw1bookstore.persistence.dao.impl.Memory.mapper;

import es.cesguiro.daw1bookstore.common.container.PublisherIoc;
import es.cesguiro.daw1bookstore.domain.model.Book;
import es.cesguiro.daw1bookstore.domain.model.Publisher;
import es.cesguiro.daw1bookstore.persistence.dao.PublisherDao;
import es.cesguiro.daw1bookstore.persistence.dao.impl.Memory.data.BookTable;
import es.cesguiro.daw1bookstore.persistence.dao.impl.Memory.data.record.BookRecord;

import java.util.ArrayList;
import java.util.List;

public class BookMapper {

    public static Book toBook(BookRecord bookRecord) {
        if(bookRecord == null) {
            return null;
        }
        Book book = new Book();
        book.setId(bookRecord.getId());
        book.setIsbn(bookRecord.getIsbn());
        book.setTitle(bookRecord.getTitle());
        book.setSynopsis(bookRecord.getSynopsis());
        book.setPrice(bookRecord.getPrice());
        book.setCover(bookRecord.getCover());

        return book;
    }

    public static List<Book> toBookList(List<BookRecord> bookRecordList) {
        List<Book> bookList = new ArrayList<>();
        for(BookRecord bookRecord : bookRecordList) {
            bookList.add(toBook(bookRecord));
        }
        return bookList;
    }

}
