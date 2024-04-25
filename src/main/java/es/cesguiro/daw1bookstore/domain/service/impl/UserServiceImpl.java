package es.cesguiro.daw1bookstore.domain.service.impl;

import es.cesguiro.daw1bookstore.common.container.UserIoc;
import es.cesguiro.daw1bookstore.domain.model.User;
import es.cesguiro.daw1bookstore.domain.service.UserService;
import es.cesguiro.daw1bookstore.persistence.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserServiceImpl implements UserService {


    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username);
    }

    @Override
    public User getActiveUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (User) authentication.getPrincipal();
    }
}
