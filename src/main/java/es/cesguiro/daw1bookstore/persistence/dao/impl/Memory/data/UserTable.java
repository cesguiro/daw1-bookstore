package es.cesguiro.daw1bookstore.persistence.dao.impl.Memory.data;

import es.cesguiro.daw1bookstore.persistence.dao.impl.Memory.data.record.UserRecord;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

public class UserTable {

    private List<UserRecord> userRecordList = List.of(
            new UserRecord(1, "admin", new BCryptPasswordEncoder().encode("admin"), "admin@bookstore.com", "Juan", "González López", "Calle Admin", true),
            new UserRecord(2, "user1", new BCryptPasswordEncoder().encode("user1"), "user1@bookstore.com", "María", "García Pérez", "Calle User1", false),
            new UserRecord(3, "user2", new BCryptPasswordEncoder().encode("user2"), "user2@bookstore.com", "Ana", "Martínez Sánchez", "Calle User2", false)
    );

    public List<UserRecord> selectAll() {
        return userRecordList;
    }

    public UserRecord selectById(Integer id) {
        for(UserRecord userRecord : userRecordList) {
            if(userRecord.getId() == id) {
                return userRecord;
            }
        }
        return null;
    }

    public UserRecord selectByUsername(String username) {
        for(UserRecord userRecord : userRecordList) {
            if(userRecord.getUsername().equals(username)) {
                return userRecord;
            }
        }
        return null;
    }
}
