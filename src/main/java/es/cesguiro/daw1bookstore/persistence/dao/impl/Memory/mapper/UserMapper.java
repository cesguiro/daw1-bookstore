package es.cesguiro.daw1bookstore.persistence.dao.impl.Memory.mapper;

import es.cesguiro.daw1bookstore.domain.model.User;
import es.cesguiro.daw1bookstore.persistence.dao.impl.Memory.data.record.UserRecord;

public class UserMapper {

    public static User toUser(UserRecord userRecord) {
        return new User(
                userRecord.getId(),
                userRecord.getUsername(),
                userRecord.getPassword(),
                userRecord.getEmail(),
                userRecord.getName(),
                userRecord.getSurname(),
                userRecord.getAddress(),
                userRecord.isAdmin()
        );
    }
}
