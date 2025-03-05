package es.cesguiro.daw1.bookstore.persistence.repository.mapper;

import es.cesguiro.daw1.bookstore.domain.model.User;
import es.cesguiro.daw1.bookstore.persistence.dao.jdbc.model.UserRecord;

public class UserMapper {

    public static User toUser(UserRecord userRecord) {
        if (userRecord == null) {
            return null;
        }
        return new User(
                userRecord.id(),
                userRecord.email(),
                userRecord.password(),
                userRecord.name(),
                userRecord.address(),
                userRecord.language(),
                userRecord.admin()
        );
    }

    public static UserRecord toUserRecord(User user) {
        if (user == null) {
            return null;
        }
        return new UserRecord(
                user.getId(),
                user.getEmail(),
                user.getPassword(),
                user.getName(),
                user.getAddress(),
                user.getLanguage(),
                user.isAdmin()
        );
    }


}
