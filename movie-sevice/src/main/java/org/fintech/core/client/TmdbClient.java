package org.fintech.core.client;

import info.movito.themoviedbapi.TmdbApi;
import info.movito.themoviedbapi.model.core.Movie;
import info.movito.themoviedbapi.model.movies.MovieDb;
import info.movito.themoviedbapi.tools.TmdbException;
import lombok.RequiredArgsConstructor;
import org.fintech.api.model.MovieDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
public class TmdbClient {

    @Value("${integration.tmdb-api.key}")
    private String API_KEY;

    private final WebClient webClient;

    private final TmdbApi tmdbApi;

    public MovieDto getMovies(){

        return webClient.get()
                .uri("/movie/2?language=en-US")
                .retrieve()
                .bodyToMono(MovieDto.class).block();

    }

    public MovieDb getMovie(int id) throws TmdbException {
       return tmdbApi.getMovies().getDetails(2,"ru");
    }
    public void getDiscover() {

    }
}
