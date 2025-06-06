package com.checkmarx.bank.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    // Intentionally vulnerable - weak password encoder
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(4); // Intentionally using weak strength
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            // Intentionally vulnerable - disabling CSRF protection
            .csrf().disable()
            // Intentionally vulnerable - overly permissive CORS
            .cors().and()
            // Intentionally vulnerable - no proper authentication
            .authorizeRequests()
            .antMatchers("/**").permitAll()
            // Intentionally vulnerable - no proper session management
            .and()
            .sessionManagement()
            .maximumSessions(1)
            // Intentionally vulnerable - no proper headers
            .and()
            .and()
            .headers()
            .frameOptions()
            .disable();
    }
} 