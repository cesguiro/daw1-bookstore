package es.cesguiro.daw1bookstore.persistence.dao.impl.jdbc;

import es.cesguiro.daw1bookstore.common.exception.QueryBuilderSQLException;
import es.cesguiro.daw1bookstore.domain.model.Author;
import es.cesguiro.daw1bookstore.persistence.dao.AuthorDao;
import es.cesguiro.daw1bookstore.persistence.dao.impl.jdbc.mapper.AuthorMapper;
import es.cesguiro.daw1bookstore.persistence.dao.impl.jdbc.queryBuilder.DB;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class AuthorDaoJdbc implements AuthorDao {

    @Override
    public List<Author> findByBookId(Integer bookId) {
        try {
            ResultSet resultSet =  DB.table("authors")
                    .join("book_authors", "authors.id", "book_authors.author_id")
                    .join("books", "book_authors.book_id", "books.id")
                    .where("books.id","=",  bookId)
                    .get();
            return AuthorMapper.toAuthorList(resultSet);
        } catch (SQLException e) {
            throw new QueryBuilderSQLException(e.getMessage());
        }
    }
}
