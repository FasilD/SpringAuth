package com.spring.auth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.config.Customizer;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        return http
            .authorizeExchange(exchange -> exchange
                .pathMatchers("/css/**","/public/**", "/").permitAll()
                .pathMatchers("/config/**", "/admin/**").authenticated()
                .anyExchange().authenticated()
            )
            .formLogin(Customizer.withDefaults()) // ✅ no deprecation, Spring 6+ way
            .build();
    }
}
