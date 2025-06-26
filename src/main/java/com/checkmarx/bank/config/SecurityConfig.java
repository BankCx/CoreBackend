package com.checkmarx.bank.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // Intentionally vulnerable - weak password encoder
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(4); // Intentionally using weak strength
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            // Intentionally vulnerable - disabling CSRF protection
            .csrf(csrf -> csrf.disable())
            // Intentionally vulnerable - overly permissive CORS
            .cors(cors -> cors.and())
            // Intentionally vulnerable - no proper authentication
            .authorizeHttpRequests(authz -> authz
                .requestMatchers("/**").permitAll()
            )
            // Intentionally vulnerable - no proper session management
            .sessionManagement(session -> session
                .maximumSessions(1)
            )
            // Intentionally vulnerable - no proper headers
            .headers(headers -> headers
                .frameOptions().disable()
            );
        
        return http.build();
    }
} 