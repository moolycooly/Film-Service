package org.fintech.config;

import info.movito.themoviedbapi.TmdbApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TmdbConfiguration {

    @Value("${integration.tmdb-api.key}")
    private String apiKey;

    @Bean
    public TmdbApi tmdbApi(@Value("${integration.tmdb-api.key}") String apiKey) {
        return new TmdbApi(apiKey);
    }

}
