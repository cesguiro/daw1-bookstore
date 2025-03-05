package es.cesguiro.daw1.bookstore.persistence.dao;

import es.cesguiro.daw1.bookstore.persistence.dao.jdbc.model.BookRecord;

import java.util.List;
import java.util.Optional;

public interface BookDao extends GenericDao<BookRecord, Long> {

    /********** DbUtil **********/
    Optional<BookRecord> findByIsbn(String isbn);

    /********** Raw SQL **********/
    Optional<BookRecord> findByIsbnRawSql(String isbn);
    List<BookRecord> findAllRawSql(int page, int size);
    List<BookRecord> findAllRawSql();
    Optional<BookRecord> findByIdRawSql(Long id);
    BookRecord saveRawSql(BookRecord bookRecord);
    void deleteByIdRawSql(Long id);
    Long countRawSql();

    /********** Query Builder **********/
    Optional<BookRecord> findByIsbnQueryBuilder(String isbn);
    List<BookRecord> findAllQueryBuilder(int page, int size);
    List<BookRecord> findAllQueryBuilder();
    Optional<BookRecord> findByIdQueryBuilder(Long id);
    BookRecord saveQueryBuilder(BookRecord bookRecord);
    void deleteByIdQueryBuilder(Long id);
    Long countQueryBuilder();


}
