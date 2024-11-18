package org.fintech.api.controller;

import info.movito.themoviedbapi.model.core.MovieResultsPage;
import lombok.RequiredArgsConstructor;
import org.fintech.api.ApiPaths;
import org.fintech.api.model.FilterCondition;
import org.fintech.core.service.MovieSchedulerService;
import org.fintech.core.service.MovieService;
import org.fintech.store.entity.MovieIndex;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class MovieController {

    private final MovieService movieService;

    private final MovieSchedulerService movieSchedulerService;

    @GetMapping(ApiPaths.GET_MOVIE_BY_ID)
    public MovieResultsPage getMovie(@PathVariable("id") int id) {
        movieSchedulerService.loadNewMovies();
        return null;
    }

    @PostMapping(ApiPaths.GET_MOVIES_BY_FILTERS)
    public Page<MovieIndex> searchMovies(
            @RequestParam("page") int page,
            @RequestParam("size") int size,
            @RequestBody List<FilterCondition> filters) {
        return movieService.findMoviesByFilters(page, size, filters);
    }
}
