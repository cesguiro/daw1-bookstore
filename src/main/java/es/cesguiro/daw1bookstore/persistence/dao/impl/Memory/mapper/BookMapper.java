package es.cesguiro.daw1bookstore.persistence.dao.impl.Memory.mapper;

import es.cesguiro.daw1bookstore.domain.model.Book;
import es.cesguiro.daw1bookstore.persistence.dao.impl.Memory.data.record.BookRecord;
import org.springframework.context.i18n.LocaleContextHolder;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class BookMapper {

    public static Book toBook(BookRecord bookRecord) {
        //String language = LanguageIoc.getLanguageManager().getCurrentLanguage();
        Locale currentLocale = LocaleContextHolder.getLocale();
        String language = currentLocale.getLanguage();

        if(bookRecord == null) {
            return null;
        }
        Book book = new Book();
        book.setId(bookRecord.getId());
        book.setIsbn(bookRecord.getIsbn());
        if("en".equals(language)) {
            book.setTitle(bookRecord.getTitleEn());
            book.setSynopsis(bookRecord.getSynopsisEn());
        } else {
            book.setTitle(bookRecord.getTitle());
            book.setSynopsis(bookRecord.getSynopsis());
        }
        //book.setTitle(bookRecord.getTitle());
        //book.setSynopsis(bookRecord.getSynopsis());
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
