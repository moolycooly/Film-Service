package org.fintech.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.fintech.api.ApiPaths;
import org.fintech.api.model.FilterCondition;
import org.fintech.core.service.MovieSchedulerService;
import org.fintech.core.service.MovieService;
import org.fintech.store.entity.MovieEntity;
import org.fintech.store.entity.MovieIndex;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@Slf4j
@Tag(name="Фильмы")
public class MovieController {

    private final MovieService movieService;

    private final MovieSchedulerService movieSchedulerService;

    @GetMapping(ApiPaths.GET_MOVIE_BY_ID)
    public MovieIndex getMovie(@PathVariable("id") int id) {
        return movieService.getMovieById(id);
    }

    @Operation(summary = "Получение похожих фильмов по идентификатору фильма")
    @GetMapping(ApiPaths.GET_SIMILAR_MOVIES)
    public Page<MovieIndex> getSimilarMovies(
            @RequestParam("page") int page,
            @RequestParam("size") int size,
            @PathVariable("id") int id) {
        return movieService.findSimilarMovies(id,page,size);
    }

    @PostMapping(ApiPaths.GET_MOVIES_BY_FILTERS)
    public Page<MovieEntity> searchMovies(
            @RequestParam("page") int page,
            @RequestParam("size") int size,
            @RequestParam(value = "sortField", defaultValue = "tmdbPopularity") String sortField,
            @RequestParam(value = "sortDirection", defaultValue = "ASC") String sortDirection,
            @RequestBody List<FilterCondition> filters) {
        return movieService.findMoviesByFilters(page, size, sortField, sortDirection, filters);
    }
}
