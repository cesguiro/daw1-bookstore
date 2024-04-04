package es.cesguiro.daw1bookstore.persistence.dao.impl.Memory;

import es.cesguiro.daw1bookstore.domain.model.User;
import es.cesguiro.daw1bookstore.persistence.dao.UserDao;
import es.cesguiro.daw1bookstore.persistence.dao.impl.Memory.data.UserTable;
import es.cesguiro.daw1bookstore.persistence.dao.impl.Memory.mapper.UserMapper;

public class UserDaoMemory implements UserDao {

    private final UserTable userTable = new UserTable();

    @Override
    public User findByUsername(String username) {
        return UserMapper.toUser(userTable.selectByUsername(username));
    }

    @Override
    public User findById(int userId) {
        return UserMapper.toUser(userTable.selectById(userId));
    }
}
