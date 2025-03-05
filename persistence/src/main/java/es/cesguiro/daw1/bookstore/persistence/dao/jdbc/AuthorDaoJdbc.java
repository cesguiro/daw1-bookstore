package es.cesguiro.daw1.bookstore.persistence.dao.jdbc;

import es.cesguiro.daw1.bookstore.persistence.dao.AuthorDao;
import es.cesguiro.daw1.bookstore.persistence.dao.db.QueryBuilder;
import es.cesguiro.daw1.bookstore.persistence.dao.jdbc.mapper.AuthorMapper;
import es.cesguiro.daw1.bookstore.persistence.dao.jdbc.model.AuthorRecord;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class AuthorDaoJdbc implements AuthorDao {

    /********** DbUtil **********/

    @Override
    public List<AuthorRecord> findAllByBookIsbn(String isbn) {
        try {
            return AuthorMapper.toAuthorRecords(
                    QueryBuilder.table("authors")
                            .select("authors.*")
                            .join("books_authors", "authors.id", "books_authors.author_id")
                            .join("books", "books_authors.book_id", "books.id")
                            .where("books.isbn", "=", isbn)
                            .get()
            );
        } catch (SQLException e) {
            throw new RuntimeException("Error finding all authors by book isbn", e);
        }
    }

    @Override
    public List<AuthorRecord> findAll(int page, int size) {
        return List.of();
    }

    @Override
    public List<AuthorRecord> findAll() {
        return List.of();
    }

    @Override
    public Optional<AuthorRecord> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public AuthorRecord save(AuthorRecord entity) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public Long count() {
        return 0L;
    }


}
