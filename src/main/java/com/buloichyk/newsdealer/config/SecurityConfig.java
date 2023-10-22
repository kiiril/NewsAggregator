package com.buloichyk.newsdealer.config;

import com.buloichyk.newsdealer.services.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

    private final UserDetailsServiceImpl usersDetailsService;

    @Autowired
    public SecurityConfig(UserDetailsServiceImpl usersDetailsService) {
        this.usersDetailsService = usersDetailsService;
    }

    @Bean
    public SecurityFilterChain applicationSecurity(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((authorize) -> authorize
                        .requestMatchers("/main", "/login", "/registration", "/login-image.jpeg", "/registration-image.jpeg", "/search", "/profile.jpeg", "/profile").permitAll()
                        .anyRequest().authenticated())
                .formLogin(form -> form
                        .loginPage("/login")
                        .loginProcessingUrl("/process_login")
                        .defaultSuccessUrl("/main", true)
                        .failureUrl("/login?error"))
                        .logout(user -> user.logoutUrl("/logout").logoutSuccessUrl("/login"));
        http.userDetailsService(usersDetailsService);

        return http.build();
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
