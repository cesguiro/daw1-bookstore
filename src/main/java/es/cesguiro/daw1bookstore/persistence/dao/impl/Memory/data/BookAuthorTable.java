package es.cesguiro.daw1bookstore.persistence.dao.impl.Memory.data;

import es.cesguiro.daw1bookstore.persistence.dao.impl.Memory.data.record.BookAuthorsRecord;

import java.util.ArrayList;
import java.util.List;

public class BookAuthorTable {

    List<BookAuthorsRecord> bookAuthorsRecordList = List.of(
            new BookAuthorsRecord(1, 1, 1),
            new BookAuthorsRecord(2, 2, 2),
            new BookAuthorsRecord(3, 3, 3),
            new BookAuthorsRecord(4, 4, 2),
            new BookAuthorsRecord(5, 5, 4),
            new BookAuthorsRecord(6, 5, 5)
    );

    public List<BookAuthorsRecord> selectByBookId(int bookId) {
        List<BookAuthorsRecord> bookAuthorsRecordList = new ArrayList<>();
        for (BookAuthorsRecord bookAuthorsRecord : this.bookAuthorsRecordList) {
            if (bookAuthorsRecord.getBookId() == bookId) {
                bookAuthorsRecordList.add(bookAuthorsRecord);
            }
        }
        return bookAuthorsRecordList;
    }

    public List<BookAuthorsRecord> selectByAuthorId(int authorId) {
        List<BookAuthorsRecord> bookAuthorsRecordList = new ArrayList<>();
        for (BookAuthorsRecord bookAuthorsRecord : this.bookAuthorsRecordList) {
            if (bookAuthorsRecord.getAuthorId() == authorId) {
                bookAuthorsRecordList.add(bookAuthorsRecord);
            }
        }
        return bookAuthorsRecordList;
    }
}
