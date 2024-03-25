package es.cesguiro.daw1bookstore.persistence.dao.impl.Memory;

import es.cesguiro.daw1bookstore.domain.model.Author;
import es.cesguiro.daw1bookstore.persistence.dao.AuthorDao;
import es.cesguiro.daw1bookstore.persistence.dao.impl.Memory.data.AuthorTable;
import es.cesguiro.daw1bookstore.persistence.dao.impl.Memory.data.BookAuthorTable;
import es.cesguiro.daw1bookstore.persistence.dao.impl.Memory.data.record.BookAuthorRecord;
import es.cesguiro.daw1bookstore.persistence.dao.impl.Memory.mapper.AuthorMapper;

import java.util.ArrayList;
import java.util.List;

public class AuthorDaoMemory implements AuthorDao {

    private AuthorTable authorTable = new AuthorTable();

    @Override
    public List<Author> findByBookId(Integer bookId) {
        List<Author> authorList = new ArrayList<>();
        BookAuthorTable bookAuthorTable = new BookAuthorTable();
        List<BookAuthorRecord> bookAuthorRecordList = bookAuthorTable.selectByBookId(bookId);
        for (BookAuthorRecord bookAuthorRecord : bookAuthorRecordList) {
            authorList.add(AuthorMapper.toAuthor(authorTable.selectById(bookAuthorRecord.getAuthorId())));
        }
        return authorList;
    }
}
