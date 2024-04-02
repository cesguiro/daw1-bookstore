package es.cesguiro.daw1bookstore.domain.service.impl;

import es.cesguiro.daw1bookstore.common.container.UserIoc;
import es.cesguiro.daw1bookstore.persistence.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailsServiceImpl() {
        this.userRepository = UserIoc.getUserRepository();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username);
    }
}
