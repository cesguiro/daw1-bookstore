package es.cesguiro.daw1bookstore.persistence.dao.impl.Memory;

import es.cesguiro.daw1bookstore.domain.model.Author;
import es.cesguiro.daw1bookstore.persistence.dao.AuthorDao;
import es.cesguiro.daw1bookstore.persistence.dao.impl.Memory.data.AuthorTable;
import es.cesguiro.daw1bookstore.persistence.dao.impl.Memory.data.BookAuthorTable;
import es.cesguiro.daw1bookstore.persistence.dao.impl.Memory.data.record.BookAuthorsRecord;
import es.cesguiro.daw1bookstore.persistence.dao.impl.Memory.mapper.AuthorMapper;

import java.util.ArrayList;
import java.util.List;

public class AuthorDaoMemory implements AuthorDao {

    private AuthorTable authorTable = new AuthorTable();

    @Override
    public List<Author> findByBookId(Integer bookId) {
        List<Author> authorList = new ArrayList<>();
        BookAuthorTable bookAuthorTable = new BookAuthorTable();
        List<BookAuthorsRecord> bookAuthorsRecordList = bookAuthorTable.selectByBookId(bookId);
        for (BookAuthorsRecord bookAuthorsRecord : bookAuthorsRecordList) {
            authorList.add(AuthorMapper.toAuthor(authorTable.selectById(bookAuthorsRecord.getAuthorId())));
        }
        return authorList;
    }
}
