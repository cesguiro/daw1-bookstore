package es.cesguiro.daw1bookstore.domain.service;

import es.cesguiro.daw1bookstore.domain.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * User service interface. It extends UserDetailsService to provide the user authentication.
 * Extends Spring Security's UserDetailsService for simplified authentication integration.
 *
 *  Note: Extending UserDetailsService breaks the clean architecture principle
 *         by introducing a dependency with Spring Security. However, it significantly
 *         simplifies authentication integration.
 */
public interface UserService extends UserDetailsService {

    //User getActiveUser();
}
