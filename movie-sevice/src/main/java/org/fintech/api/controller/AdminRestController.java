package org.fintech.api.controller;

import lombok.RequiredArgsConstructor;
import org.fintech.api.ApiPaths;
import org.fintech.core.scheduler.MovieSchedulerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AdminRestController {


    private final MovieSchedulerService movieSchedulerService;

    @GetMapping(ApiPaths.MOVIE_LOAD_START)
    public ResponseEntity<String> loadMovieStart() {
        Thread.startVirtualThread(movieSchedulerService::loadNewMovies);
        return ResponseEntity.ok("Фильмы начали загружаться");
    }

}
