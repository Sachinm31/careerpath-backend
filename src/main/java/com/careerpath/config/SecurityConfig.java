package com.careerpath.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/auth/**").permitAll()   // login allowed
                        .requestMatchers("/api/users/register").permitAll() // register allowed
                        .anyRequest().permitAll()   // allow everything (for now)
                )
                .httpBasic(httpBasic -> httpBasic.disable()) // disable browser popup
                .formLogin(form -> form.disable());          // disable login form

        return http.build();
    }
}
