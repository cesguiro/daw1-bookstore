package es.cesguiro.daw1bookstore.config;

import es.cesguiro.daw1bookstore.domain.model.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // Allow access to static resources
    @Bean
    WebSecurityCustomizer configureWebSecurity() {
        return (web) -> web.ignoring().requestMatchers("/images/**", "/js/**", "/css/**", "/webjars/**");
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .authorizeHttpRequests((request) -> request
                        .requestMatchers("/", "/books/**").permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin((form) -> form
                        //.loginProcessingUrl("/login")
                        //.loginPage("/login")
                        //.failureUrl("/login?error")
                        .defaultSuccessUrl("/")
                        .permitAll()
                )
                .logout((logout) -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/")
                        .permitAll()
                )
                .sessionManagement((session) -> session
                        .maximumSessions(1) // Permitir solo una sesión por usuario
                        .maxSessionsPreventsLogin(false) // Permitir que un nuevo inicio de sesión cierre la sesión anterior
                        .expiredUrl("/login?expired") // Redirigir a esta URL si la sesión expira
                )
                .build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        // Passwords are not encrypted for simplicity
        // In a real application, passwords should be encrypted
        // {noop} is a prefix that indicates that the password is not encrypted
        User admin = new User(1, "admin", "{noop}admin", "admin@bookstore.com", "María", "González", "Calle Mayor, 1", true);
        User user1 = new User(2, "user1", "{noop}user1", "user1@bookstore.com", "Juan", "Pérez", "Calle Mayor, 2", false);
        User user2 = new User(3, "user2", "{noop}user2", "user2@bookstore.com", "Ana", "Martínez", "Calle Mayor, 3", false);

        return new InMemoryUserDetailsManager(admin, user1, user2);
    }

}
