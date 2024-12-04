package org.fintech.config;

import org.fintech.api.ApiPaths;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    private static final String[] AUTH_WHITELIST = {
            "/swagger-resources/*",
            "/swagger-ui/**",
            "/v3/api-docs/**",
            ApiPaths.REGISTRATION,
            ApiPaths.AUTHORIZATION,
            ApiPaths.TOKEN_VALIDATE,
            ApiPaths.ACTIVATE_ACCOUNT
    };
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(request -> request
                        .requestMatchers(AUTH_WHITELIST).permitAll()
                        .anyRequest().denyAll());
        return http.build();
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
