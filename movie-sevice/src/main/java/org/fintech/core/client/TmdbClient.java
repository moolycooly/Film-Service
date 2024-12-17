package org.fintech.core.client;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.fintech.core.model.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

import java.time.Duration;

@Slf4j
@Service
public class TmdbClient {

    @Qualifier("tmdbWebClient")
    @Autowired
    private WebClient webClient;

    @Getter
    @Value("${integration.tmdb-api.retry.max-attempts}")
    private Integer baseMaxAttempts;

    @Getter
    @Value("${integration.tmdb-api.retry.delay}")
    private Integer baseRetryDelay;

    public Mono<Movie> getMovie(long id) {
        String uri = UriComponentsBuilder.fromPath("/movie/" + id)
                .queryParam("append_to_response", "id,videos,images,keywords,credits")
                .queryParam("language","ru")
                .toUriString();
        return webClient.get()
                .uri(uri)
                .retrieve()
                .bodyToMono(Movie.class)
                .onErrorResume(WebClientResponseException.class,
                        ex -> ex.getStatusCode().equals(HttpStatus.NOT_FOUND) ? Mono.empty() : Mono.error(ex))
                .retryWhen(Retry.backoff(baseMaxAttempts, Duration.ofMillis(baseRetryDelay)))
                .doOnError(error-> {
                    log.error("Error to get movie from TMDB: {}", error.getMessage());
                });
    }

    public Mono<Movie> getLatestMovie() {
        String uri = UriComponentsBuilder.fromPath("/movie/latest").toUriString();
        return webClient.get()
                .uri(uri)
                .retrieve()
                .bodyToMono(Movie.class)
                .retryWhen(Retry.backoff(baseMaxAttempts, Duration.ofMillis(baseRetryDelay)))
                .doOnError(error-> {
                    log.error("Error to get latest movie from TMDB: {}", error.getMessage());
                });
    }

}
