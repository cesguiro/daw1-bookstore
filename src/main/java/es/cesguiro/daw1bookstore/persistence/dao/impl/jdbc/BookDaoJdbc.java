package es.cesguiro.daw1bookstore.persistence.dao.impl.jdbc;

import es.cesguiro.daw1bookstore.common.exception.QueryBuilderSQLException;
import es.cesguiro.daw1bookstore.domain.model.Book;
import es.cesguiro.daw1bookstore.persistence.dao.BookDao;
import es.cesguiro.daw1bookstore.persistence.dao.impl.jdbc.mapper.BookMapper;
import es.cesguiro.daw1bookstore.persistence.dao.impl.jdbc.queryBuilder.DB;
import org.springframework.context.i18n.LocaleContextHolder;

import java.sql.ResultSet;
import java.util.List;
import java.util.Locale;

public class BookDaoJdbc implements BookDao {

    @Override
    public List<Book> findAll() {
        Locale currentLocale = LocaleContextHolder.getLocale();
        String language = currentLocale.getLanguage();
        try {
            ResultSet resultSet = DB
                    .table("books")
                    .select("id", "isbn", "title_" + language + " AS title", "synopsis_" + language + " AS synopsis", "price", "cover")
                    .get();
            return BookMapper.toBookList(resultSet);
        } catch (Exception e) {
            throw new QueryBuilderSQLException(e.getMessage());
        }
    }

    @Override
    public Book findById(Integer id) {
        Locale currentLocale = LocaleContextHolder.getLocale();
        String language = currentLocale.getLanguage();
        try {
            ResultSet resultSet = DB
                    .table("books")
                    .select("id", "isbn", "title_" + language + " AS title", "synopsis_" + language + " AS synopsis", "price", "cover")
                    .find(id);
            return BookMapper.toBook(resultSet);
        } catch (Exception e) {
            throw new QueryBuilderSQLException(e.getMessage());
        }
    }
}
