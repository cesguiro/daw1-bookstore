package es.cesguiro.daw1.bookstore.persistence.dao.jdbc;

import es.cesguiro.daw1.bookstore.persistence.dao.BookDao;
import es.cesguiro.daw1.bookstore.persistence.dao.db.QueryBuilder;
import es.cesguiro.daw1.bookstore.persistence.dao.db.RawSql;
import es.cesguiro.daw1.bookstore.persistence.dao.jdbc.mapper.BookMapper;
import es.cesguiro.daw1.bookstore.persistence.dao.jdbc.model.BookRecord;
import es.cesguiro.daw1.bookstore.util.context.RequestContextHolder;
import es.cesguiro.daw1.bookstore.util.exception.Error500;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class BookDaoJdbc implements BookDao {

    /********** DbUtil **********/

    @Override
    public Optional<BookRecord> findByIsbn(String isbn) {
        String query = "SELECT * FROM books WHERE isbn = ?";
        try {
            PreparedStatement preparedStatement = RequestContextHolder.getRequestContext().getConnection().prepareStatement(query);
            preparedStatement.setString(1, isbn);
            preparedStatement.executeQuery();
            ResultSet resultSet = preparedStatement.getResultSet();
            if (!resultSet.next()) {
                return Optional.empty();
            }
            return Optional.of(BookMapper.toBookRecord(resultSet));
        } catch (SQLException e) {
            throw new Error500("Error finding book by isbn", e);
        }
    }


    @Override
    public List<BookRecord> findAll(int page, int size) {
        String query = "SELECT * FROM books LIMIT ? OFFSET ?";
        try {
            PreparedStatement preparedStatement = RequestContextHolder.getRequestContext().getConnection().prepareStatement(query);
            preparedStatement.setInt(1, size);
            preparedStatement.setInt(2, (page - 1) * size);
            preparedStatement.executeQuery();
            return BookMapper.toBookRecords(preparedStatement.getResultSet());
        } catch (SQLException e) {
            throw new Error500("Error finding all books", e);
        }
    }

    @Override
    public List<BookRecord> findAll() {
        String query = "SELECT * FROM books";
        try {
            PreparedStatement preparedStatement = RequestContextHolder.getRequestContext().getConnection().prepareStatement(query);
            preparedStatement.executeQuery();
            return BookMapper.toBookRecords(preparedStatement.getResultSet());
        } catch (SQLException e) {
            throw new Error500("Error finding all books", e);
        }
    }

    @Override
    public Optional<BookRecord> findById(Long id) {
        String query = "SELECT * FROM books WHERE id = ?";
        try {
            PreparedStatement preparedStatement = RequestContextHolder.getRequestContext().getConnection().prepareStatement(query);
            preparedStatement.setLong(1, id);
            preparedStatement.executeQuery();
            ResultSet resultSet = preparedStatement.getResultSet();
            if (!resultSet.next()) {
                return Optional.empty();
            }
            return Optional.of(BookMapper.toBookRecord(resultSet));
        } catch (SQLException e) {
            throw new Error500("Error finding book by id", e);
        }
    }

    @Override
    public BookRecord save(BookRecord bookRecord) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public Long count() {
        String query = "SELECT COUNT(*) FROM books";
        try {
            PreparedStatement preparedStatement = RequestContextHolder.getRequestContext().getConnection().prepareStatement(query);
            preparedStatement.executeQuery();
            ResultSet resultSet = preparedStatement.getResultSet();
            if (!resultSet.next()) {
                return 0L;
            }
            return resultSet.getLong(1);
        } catch (SQLException e) {
            throw new Error500("Error counting books", e);
        }
    }

    /********** RawSql **********/


    @Override
    public Optional<BookRecord> findByIsbnRawSql(String isbn) {
        String query = "SELECT * FROM books WHERE isbn = ?";
        try {
            ResultSet resultSet = RawSql.select(query, List.of(isbn));
            if (!resultSet.next()) {
                return Optional.empty();
            }
            return Optional.of(BookMapper.toBookRecord(resultSet));
        } catch (Exception e) {
            throw new Error500("Error finding book by isbn", e);
        }
    }

    @Override
    public List<BookRecord> findAllRawSql(int page, int size) {
        String query = "SELECT * FROM books LIMIT ? OFFSET ?";
        try {
            return BookMapper.toBookRecords(RawSql.select(query, List.of(size, (page - 1) * size)));
        } catch (Exception e) {
            throw new Error500("Error finding all books", e);
        }
    }

    @Override
    public List<BookRecord> findAllRawSql() {
        String query = "SELECT * FROM books";
        try {
            return BookMapper.toBookRecords(RawSql.select(query, null));
        } catch (Exception e) {
            throw new Error500("Error finding all books", e);
        }
    }

    @Override
    public Optional<BookRecord> findByIdRawSql(Long id) {
        String query = "SELECT * FROM books WHERE id = ?";
        try {
            ResultSet resultSet = RawSql.select(query, List.of(id));
            if (!resultSet.next()) {
                return Optional.empty();
            }
            return Optional.of(BookMapper.toBookRecord(resultSet));
        } catch (Exception e) {
            throw new Error500("Error finding book by id", e);
        }
    }

    @Override
    public BookRecord saveRawSql(BookRecord entity) {
        return null;
    }

    @Override
    public void deleteByIdRawSql(Long id) {

    }

    @Override
    public Long countRawSql() {
        return 0L;
    }

    /************* QueryBuilder *************/

    @Override
    public Optional<BookRecord> findByIsbnQueryBuilder(String isbn) {
        try{
            return Optional.ofNullable(BookMapper.toBookRecord(QueryBuilder.table("books").where("isbn", "=", isbn).getOne()));
        } catch (SQLException e) {
            throw new Error500("Error finding book by isbn", e);
        }
    }

    @Override
    public List<BookRecord> findAllQueryBuilder(int page, int size) {
        try {
            return BookMapper.toBookRecords(QueryBuilder.table("books").page(page, size).get());
        } catch (SQLException e) {
            throw new Error500("Error finding all books", e);
        }
    }

    @Override
    public List<BookRecord> findAllQueryBuilder() {
        try {
            return BookMapper.toBookRecords(QueryBuilder.table("books").get());
        } catch (SQLException e) {
            throw new Error500("Error finding all books", e);
        }
    }

    @Override
    public Optional<BookRecord> findByIdQueryBuilder(Long id) {
        try {
            return Optional.ofNullable(BookMapper.toBookRecord(QueryBuilder.table("books").find(id)));
        } catch (SQLException e) {
            throw new Error500("Error finding book by id", e);
        }
    }

    @Override
    public BookRecord saveQueryBuilder(BookRecord entity) {
        return null;
    }

    @Override
    public void deleteByIdQueryBuilder(Long id) {

    }

    @Override
    public Long countQueryBuilder() {
        return 0L;
    }
}


