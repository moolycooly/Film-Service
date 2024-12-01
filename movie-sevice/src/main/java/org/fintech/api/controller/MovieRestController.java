package org.fintech.api.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.fintech.api.ApiPaths;
import org.fintech.api.model.CreateMovieRequest;
import org.fintech.api.model.MovieDto;
import org.fintech.api.model.UpdateMovieRequest;
import org.fintech.core.service.MovieSchedulerService;
import org.fintech.core.service.MovieService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@RestController
@Slf4j
@Tag(name="Фильмы")
public class MovieRestController {

    private final MovieService movieService;

    private final MovieSchedulerService movieSchedulerService;

    @PostMapping(ApiPaths.MOVIE)
    public void createMovie(@RequestBody @Valid CreateMovieRequest createMovieRequest) {
        movieService.create(createMovieRequest);
    }

    @GetMapping
    public void load() {
        movieSchedulerService.loadNewMovies();
    }

    @GetMapping(ApiPaths.MOVIE_BY_ID)
    public MovieDto getMovie(
            @PathVariable("id") long id,
            @RequestParam(value = "fields",required = false) List<String> fields
    ) {
        return movieService.getMovie(id,fields);
    }

    @GetMapping(ApiPaths.MOVIE)
    public Page<MovieDto> searchMovies(
            @RequestParam(value = "adult", required = false) Boolean adult,
            @RequestParam(value = "releaseDateGte", required = false) LocalDate releaseDateGte,
            @RequestParam(value = "releaseDateLte", required = false) LocalDate releaseDateLte,
            @RequestParam(value = "genresShould", required = false) List<String> genresShould,
            @RequestParam(value = "genresMust", required = false) List<String> genresMust,
            @RequestParam(value = "genresMustNot", required = false) List<String> genresMustNot,
            Pageable pageable
    ) {
        return movieService.searchMovies(adult, releaseDateGte, releaseDateLte, genresShould, genresMust,genresMustNot, pageable);
    }

    @RequestMapping(value =ApiPaths.MOVIE_BY_ID, method = {RequestMethod.PUT, RequestMethod.PATCH})
    public void updateMovie(@PathVariable("id") long id,
                            @RequestBody @Valid UpdateMovieRequest updateMovieRequest
    ) {
        movieService.updateMovie(updateMovieRequest,id);
    }

    @DeleteMapping(ApiPaths.MOVIE_BY_ID)
    public void deleteMovie(@PathVariable("id") long id) {
        movieService.deleteMovie(id);
    }

}
