package es.cesguiro.daw1bookstore.persistence.dao.impl.Memory;

import es.cesguiro.daw1bookstore.domain.model.Book;
import es.cesguiro.daw1bookstore.persistence.dao.BookDao;
import es.cesguiro.daw1bookstore.persistence.dao.impl.Memory.data.BookTable;
import es.cesguiro.daw1bookstore.persistence.dao.impl.Memory.data.record.BookRecord;
import es.cesguiro.daw1bookstore.persistence.dao.impl.Memory.mapper.BookMapper;

import java.math.BigDecimal;
import java.util.List;

public class BookDaoMemory implements BookDao {

    private BookTable bookTable = new BookTable();


    @Override
    public List<Book> findAll() {
        List<BookRecord> bookRecordList = bookTable.selectAll();
        return BookMapper.toBookList(bookRecordList);
    }

    @Override
    public Book findById(Integer id) {
        return BookMapper.toBook(bookTable.selectById(id));
    }
}
