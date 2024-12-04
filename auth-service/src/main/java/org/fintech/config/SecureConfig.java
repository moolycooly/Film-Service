package org.fintech.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.security.SecureRandom;

@Configuration
public class SecureConfig {

    @Bean
    public SecureRandom getSecureRandom() {
        return new SecureRandom();
    }

}
