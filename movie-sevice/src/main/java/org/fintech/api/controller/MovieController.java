package org.fintech.api.controller;

import info.movito.themoviedbapi.model.core.MovieResultsPage;
import info.movito.themoviedbapi.tools.TmdbException;
import lombok.RequiredArgsConstructor;
import org.fintech.api.ApiPaths;
import org.fintech.api.model.MovieDto;
import org.fintech.core.client.TmdbClient;
import org.fintech.core.service.MovieService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RequiredArgsConstructor
@RestController
public class MovieController {

    private final MovieService movieService;

    private final TmdbClient tmdbClient;

    @GetMapping(ApiPaths.GET_MOVIE_BY_ID)
    public MovieResultsPage getMovie(@PathVariable("id") int id) {

    }
}
