package org.fintech.core.service;

import info.movito.themoviedbapi.TmdbApi;
import info.movito.themoviedbapi.model.core.Genre;
import info.movito.themoviedbapi.model.core.Movie;
import info.movito.themoviedbapi.model.core.MovieResultsPage;
import info.movito.themoviedbapi.tools.TmdbException;
import info.movito.themoviedbapi.tools.builders.discover.DiscoverMovieParamBuilder;
import lombok.RequiredArgsConstructor;
import org.fintech.core.mapper.MovieMapper;
import org.fintech.store.entity.GenreEntity;
import org.fintech.store.entity.MovieEntity;
import org.fintech.store.repository.GenreRepository;
import org.fintech.store.repository.MovieRepository;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;

    private final GenreRepository genreRepository;

    private final TmdbApi tmdbApi;

    private final MovieMapper movieMapper;


    public void saveMovies(List<Movie> movieList) {
        List<Integer> allGenreIds = movieList.stream()
                .flatMap(movie -> movie.getGenreIds().stream())
                .toList();
        List<GenreEntity> genres = genreRepository.findAllById(allGenreIds);
        List<MovieEntity> movieEntities = new ArrayList<>();
        for (Movie movie : movieList) {
            Set<GenreEntity> movieGenres = genres.stream()
                    .filter(genre -> movie.getGenreIds().contains(genre.getId()))
                    .collect(Collectors.toSet());
            MovieEntity movieEntity = movieMapper.map(movie);
            movieEntity.setGenres(movieGenres);
            movieEntities.add(movieEntity);
        }
       movieRepository.saveAll(movieEntities);
    }

}

