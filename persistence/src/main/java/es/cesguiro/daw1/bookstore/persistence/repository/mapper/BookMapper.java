package es.cesguiro.daw1.bookstore.persistence.repository.mapper;

import es.cesguiro.daw1.bookstore.domain.model.Book;
import es.cesguiro.daw1.bookstore.domain.model.vo.LocaleString;
import es.cesguiro.daw1.bookstore.persistence.dao.jdbc.model.BookRecord;

public class BookMapper {

    public static Book toBook(BookRecord bookRecord) {
        if (bookRecord == null) {
            return null;
        }
        return new Book(
                bookRecord.isbn(),
                new LocaleString(bookRecord.titleEs(), bookRecord.titleEn()),
                new LocaleString(bookRecord.synopsisEs(), bookRecord.synopsisEn()),
                bookRecord.basePrice(),
                bookRecord.discountPercentage(),
                bookRecord.cover(),
                bookRecord.publicationDate()
        );
    }
}
