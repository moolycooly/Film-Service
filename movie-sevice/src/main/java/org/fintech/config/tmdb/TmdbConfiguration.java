package org.fintech.config.tmdb;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@RequiredArgsConstructor
public class TmdbConfiguration {

    private final TmdbProperties tmdbProperties;

    @Bean
    public WebClient tmdbWebClient() {
        return WebClient.builder()
                .baseUrl(tmdbProperties.getTmdbBaseUrl())
                .defaultHeader("Authorization", "Bearer " + tmdbProperties.getApiKey())
                .defaultHeader("accept", "application/json")
                .build();
    }


}
