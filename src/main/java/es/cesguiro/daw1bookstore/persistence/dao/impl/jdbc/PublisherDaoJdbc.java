package es.cesguiro.daw1bookstore.persistence.dao.impl.jdbc;

import es.cesguiro.daw1bookstore.common.exception.QueryBuilderSQLException;
import es.cesguiro.daw1bookstore.domain.model.Publisher;
import es.cesguiro.daw1bookstore.persistence.dao.PublisherDao;
import es.cesguiro.daw1bookstore.persistence.dao.impl.jdbc.mapper.PublisherMapper;
import es.cesguiro.daw1bookstore.persistence.dao.impl.jdbc.queryBuilder.DB;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PublisherDaoJdbc implements PublisherDao {
    @Override
    public Publisher findByBookId(Integer bookId) {
        try {
            ResultSet resultSet =  DB.table("publishers")
                    .join("books", "publishers.id", "books.publisher_id")
                    .where("books.id","=",  bookId)
                    .get();
            if(!resultSet.next()) {
                return null;
            }
            return PublisherMapper.toPublisher(resultSet);
        } catch (SQLException e) {
            throw new QueryBuilderSQLException(e.getMessage());
        }
    }
}
