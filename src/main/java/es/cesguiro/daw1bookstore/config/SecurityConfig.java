package es.cesguiro.daw1bookstore.config;

import es.cesguiro.daw1bookstore.common.container.UserIoc;
import es.cesguiro.daw1bookstore.common.exception.AuthorizationException;
import es.cesguiro.daw1bookstore.domain.service.impl.UserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
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
                        .requestMatchers("/orders", "/carts").hasAuthority("ROLE_USER")
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
                .exceptionHandling((exception) -> exception
                        .accessDeniedHandler(accessDeniedHandler())
                )
                .build();
    }

    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return (request, response, accessDeniedException) -> {
            throw new AuthorizationException("Access denied");
        };
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return UserIoc.getUserService();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
