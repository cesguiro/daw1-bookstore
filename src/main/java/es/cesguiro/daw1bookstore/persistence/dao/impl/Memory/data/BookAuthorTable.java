package es.cesguiro.daw1bookstore.persistence.dao.impl.Memory.data;

import es.cesguiro.daw1bookstore.persistence.dao.impl.Memory.data.record.BookAuthorRecord;

import java.util.ArrayList;
import java.util.List;

public class BookAuthorTable {

    List<BookAuthorRecord> bookAuthorRecordList = List.of(
            new BookAuthorRecord(1, 1, 1),
            new BookAuthorRecord(2, 2, 2),
            new BookAuthorRecord(3, 3, 3),
            new BookAuthorRecord(4, 4, 2),
            new BookAuthorRecord(5, 5, 4),
            new BookAuthorRecord(6, 5, 5)
    );

    public List<BookAuthorRecord> selectByBookId(int bookId) {
        List<BookAuthorRecord> bookAuthorRecordList = new ArrayList<>();
        for (BookAuthorRecord bookAuthorRecord : this.bookAuthorRecordList) {
            if (bookAuthorRecord.getBookId() == bookId) {
                bookAuthorRecordList.add(bookAuthorRecord);
            }
        }
        return bookAuthorRecordList;
    }

    public List<BookAuthorRecord> selectByAuthorId(int authorId) {
        List<BookAuthorRecord> bookAuthorRecordList = new ArrayList<>();
        for (BookAuthorRecord bookAuthorRecord : this.bookAuthorRecordList) {
            if (bookAuthorRecord.getAuthorId() == authorId) {
                bookAuthorRecordList.add(bookAuthorRecord);
            }
        }
        return bookAuthorRecordList;
    }
}
