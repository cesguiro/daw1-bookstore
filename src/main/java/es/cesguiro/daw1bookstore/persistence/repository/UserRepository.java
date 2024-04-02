package es.cesguiro.daw1bookstore.persistence.repository;

import es.cesguiro.daw1bookstore.domain.model.User;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository {
    User findByUsername(String username);
}
