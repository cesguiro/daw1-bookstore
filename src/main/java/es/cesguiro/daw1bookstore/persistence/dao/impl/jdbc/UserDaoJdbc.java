package es.cesguiro.daw1bookstore.persistence.dao.impl.jdbc;

import es.cesguiro.daw1bookstore.common.exception.QueryBuilderSQLException;
import es.cesguiro.daw1bookstore.domain.model.User;
import es.cesguiro.daw1bookstore.persistence.dao.UserDao;
import es.cesguiro.daw1bookstore.persistence.dao.impl.jdbc.mapper.BookMapper;
import es.cesguiro.daw1bookstore.persistence.dao.impl.jdbc.mapper.UserMapper;
import es.cesguiro.daw1bookstore.persistence.dao.impl.jdbc.queryBuilder.DB;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.sql.ResultSet;

public class UserDaoJdbc implements UserDao {

    @Override
    public User findByUsername(String username) {
        try {
            ResultSet resultSet = DB
                    .table("users")
                    .where("username", "=", username)
                    .get();
            resultSet.next();
            return UserMapper.toUser(resultSet);
        } catch (Exception e) {
            throw new QueryBuilderSQLException(e.getMessage());
        }
    }

    @Override
    public User findById(int userId) {
        try {
            ResultSet resultSet = DB
                    .table("users")
                    .find(userId);
            return UserMapper.toUser(resultSet);
        } catch (Exception e) {
            throw new QueryBuilderSQLException(e.getMessage());
        }
    }
}
