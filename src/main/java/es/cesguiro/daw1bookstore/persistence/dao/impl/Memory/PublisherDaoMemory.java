package es.cesguiro.daw1bookstore.persistence.dao.impl.Memory;

import es.cesguiro.daw1bookstore.common.container.BookIoc;
import es.cesguiro.daw1bookstore.domain.model.Book;
import es.cesguiro.daw1bookstore.domain.model.Publisher;
import es.cesguiro.daw1bookstore.persistence.dao.BookDao;
import es.cesguiro.daw1bookstore.persistence.dao.PublisherDao;
import es.cesguiro.daw1bookstore.persistence.dao.impl.Memory.data.BookTable;
import es.cesguiro.daw1bookstore.persistence.dao.impl.Memory.data.PublisherTable;
import es.cesguiro.daw1bookstore.persistence.dao.impl.Memory.data.record.BookRecord;
import es.cesguiro.daw1bookstore.persistence.dao.impl.Memory.data.record.PublisherRecord;
import es.cesguiro.daw1bookstore.persistence.dao.impl.Memory.mapper.PublisherMapper;

import java.util.List;

public class PublisherDaoMemory implements PublisherDao {

    private PublisherTable publisherTable = new PublisherTable();

    @Override
    public Publisher findByBookId(Integer bookId) {
        BookTable bookTable = new BookTable();
        BookRecord bookRecord = bookTable.selectById(bookId);
        if(bookRecord == null) {
            return null;
        }
        int publisherId = bookRecord.getPublisherId();
        return PublisherMapper.toPublisher(publisherTable.selectById(publisherId));
    }
}
