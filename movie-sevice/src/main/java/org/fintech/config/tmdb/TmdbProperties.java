package org.fintech.config.tmdb;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TmdbProperties {

    @Getter
    @Value("${integration.tmdb-api.url}")
    private String tmdbBaseUrl;

    @Getter
    @Value("${integration.tmdb-api.url-image}")
    private String tmdbImageUrl;

    @Getter
    @Value("${integration.tmdb-api.key}")
    private String apiKey;

}
