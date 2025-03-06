package es.cesguiro.daw1.bookstore.persistence.dao.jdbc;

import es.cesguiro.daw1.bookstore.domain.model.User;
import es.cesguiro.daw1.bookstore.persistence.dao.UserDao;
import es.cesguiro.daw1.bookstore.persistence.dao.db.QueryBuilder;
import es.cesguiro.daw1.bookstore.persistence.dao.db.RawSql;
import es.cesguiro.daw1.bookstore.persistence.dao.jdbc.mapper.UserMapper;
import es.cesguiro.daw1.bookstore.persistence.dao.jdbc.model.UserRecord;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class UserDaoJdbc implements UserDao {

    @Override
    public Optional<UserRecord> findByEmail(String email) {
        try {
            return Optional.ofNullable(UserMapper.toUserRecord(QueryBuilder.table("users").where("email", "=", email).getOne()));
        } catch (SQLException e) {
            throw new RuntimeException("Error finding user by email", e);
        }

    }

    @Override
    public void storeToken(UserRecord userRecord, String token) {
        String query = """
            INSERT INTO tokens (user_id, token, created_at, expires_at)
            VALUES (?, ?, NOW(), NOW() + INTERVAL 1 HOUR)
            """;
        RawSql.update(
                query,
                List.of(userRecord.id(), token)
        );
    }

    @Override
    public Optional<UserRecord> findByToken(String token) {
        String query = """
            SELECT u.id, u.email, u.password, u.name, u.address, u.language, u.admin
            FROM users u
            JOIN tokens t ON u.id = t.user_id
            WHERE t.token = ?
            """;
        try {
            ResultSet resultSet = RawSql.select(query, List.of(token));
            if (!resultSet.next()) {
                return Optional.empty();
            }
            return Optional.of(UserMapper.toUserRecord(resultSet));
        } catch (SQLException e) {
            throw new RuntimeException("Error finding user by token", e);
        }
    }

    @Override
    public List<UserRecord> findAll(int page, int size) {
        return List.of();
    }

    @Override
    public List<UserRecord> findAll() {
        return List.of();
    }

    @Override
    public Optional<UserRecord> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public UserRecord save(UserRecord userRecord) {
        String query = """
            INSERT INTO users (email, password, name, address, language, admin) 
            VALUES (?, ?, ?, ?, ?, ?)
            """;
        Long id = RawSql.insert(
                query ,
                List.of(
                        userRecord.email(),
                        userRecord.password(),
                        userRecord.name(),
                        userRecord.address(),
                        userRecord.language(),
                        userRecord.admin()
                )
        );
        return new UserRecord(
                id,
                userRecord.email(),
                userRecord.password(),
                userRecord.name(),
                userRecord.address(),
                userRecord.language(),
                userRecord.admin()
        );
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public Long count() {
        return 0L;
    }
}
