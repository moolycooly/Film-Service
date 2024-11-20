package org.fintech.core.service;

import info.movito.themoviedbapi.TmdbApi;
import info.movito.themoviedbapi.model.core.Genre;
import info.movito.themoviedbapi.model.core.Movie;
import info.movito.themoviedbapi.model.core.MovieResultsPage;
import info.movito.themoviedbapi.tools.TmdbException;
import info.movito.themoviedbapi.tools.builders.discover.DiscoverMovieParamBuilder;
import lombok.RequiredArgsConstructor;
import org.fintech.store.entity.GenreEntity;
import org.fintech.store.repository.GenreRepository;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@EnableScheduling
public class MovieSchedulerService {

    private final MovieService movieService;

    private final GenreRepository genreRepository;

    private final TmdbApi tmdbApi;

    @Scheduled(cron = "${scheduled.task.update-db}")
    public void loadNewMovies() {
        event();
        LocalDate today = LocalDate.now();
        LocalDate yesterday = today.minusDays(1);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            List<Movie> movieResult = new ArrayList<>();
            int page = 1;
            int totalPages = 1;
            while (page <= totalPages){
                MovieResultsPage movies = tmdbApi.getDiscover()
                        .getMovie(new DiscoverMovieParamBuilder()
                                .releaseDateGte(yesterday.format(formatter))
                                .releaseDateLte(today.format(formatter))
                                .language("ru")
                                .page(page)
                        );
                movieResult.addAll(movies.getResults());
                totalPages = movies.getTotalPages();
                page++;
            }
            movieService.saveMovies(movieResult);

        } catch (TmdbException e) {
            e.printStackTrace();
        }
    }
    public void event(){
        try {
            List<Genre> genres = tmdbApi.getGenre().getMovieList("ru");
            for (Genre genre : genres) {
                var entity = GenreEntity.builder().tmdbId(genre.getId()).name(genre.getName()).build();
                genreRepository.save(entity);

            }
        }catch (TmdbException e){

        }
    }
}
