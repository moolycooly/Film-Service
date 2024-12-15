package org.fintech.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.fintech.api.ApiPaths;
import org.fintech.api.model.CreateMovieRequest;
import org.fintech.api.model.MovieDto;
import org.fintech.api.model.UpdateMovieRequest;
import org.fintech.core.scheduler.MovieSchedulerService;
import org.fintech.core.service.MovieService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@RestController
@Slf4j
@Tag(name = "Фильмы", description = "API для управления фильмами")
public class MovieRestController {

    private final MovieService movieService;

    @Operation(summary = "Создать новый фильм", description = "Создаёт новый фильм на основе данных запроса")
    @PostMapping(ApiPaths.MOVIE)
    public void createMovie(
            @RequestBody @Valid @Parameter(description = "Данные для создания фильма") CreateMovieRequest createMovieRequest) {
        movieService.create(createMovieRequest);
    }

    @Operation(summary = "Получить информацию о фильме", description = "Возвращает данные фильма по указанному ID")
    @GetMapping(ApiPaths.MOVIE_BY_ID)
    public MovieDto getMovie(
            @PathVariable("id") long id,
            @RequestParam(value = "fields", required = false) List<String> fields) {
        return movieService.getMovie(id, fields);
    }

    @Operation(summary = "Поиск фильмов",
            description = "Возвращает список фильмов, соответствующих заданным критериям поиска")
    @GetMapping(ApiPaths.MOVIE_SEARCH)
    public Page<MovieDto> searchMovies(
            @Parameter(description = "Фильмы для взрослых", example = "true")
            @RequestParam(value = "adult", required = false) Boolean adult,
            @Parameter(description = "Дата релиза после указанной (включительно)", example = "2023-01-01")
            @RequestParam(value = "releaseDateGte", required = false) LocalDate releaseDateGte,
            @Parameter(description = "Дата релиза до указанной (включительно)", example = "2024-12-31")
            @RequestParam(value = "releaseDateLte", required = false) LocalDate releaseDateLte,
            @Parameter(description = "Жанры, которые должны быть в фильме", example = "[\"комедия\"]")
            @RequestParam(value = "genresShould", required = false) List<String> genresShould,
            @Parameter(description = "Жанры, которые обязательно должны быть в фильме", example = "[\"драма\"]")
            @RequestParam(value = "genresMust", required = false) List<String> genresMust,
            @Parameter(description = "Жанры, которых не должно быть в фильме", example = "[\"ужасы\"]")
            @RequestParam(value = "genresMustNot", required = false) List<String> genresMustNot,
            Pageable pageable) {
        return movieService.searchMovies(adult, releaseDateGte, releaseDateLte, genresShould, genresMust, genresMustNot, pageable);
    }

    @Operation(summary = "Обновить фильм",
            description = "Обновляет информацию о фильме по указанному ID")
    @RequestMapping(value = ApiPaths.MOVIE_BY_ID, method = {RequestMethod.PUT, RequestMethod.PATCH})
    public void updateMovie(
            @Parameter(description = "ID фильма", example = "1") @PathVariable("id") long id,
            @RequestBody @Valid @Parameter(description = "Данные для обновления фильма") UpdateMovieRequest updateMovieRequest) {
        movieService.updateMovie(updateMovieRequest, id);
    }

    @Operation(summary = "Удалить фильм", description = "Удаляет фильм по указанному ID")
    @DeleteMapping(ApiPaths.MOVIE_BY_ID)
    public void deleteMovie(
            @Parameter(description = "ID фильма", example = "1") @PathVariable("id") long id) {
        movieService.deleteMovie(id);
    }
}
