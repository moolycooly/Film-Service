package org.fintech.core.scheduler;

import lombok.RequiredArgsConstructor;

import org.fintech.core.client.TmdbClient;
import org.fintech.core.model.Movie;
import org.fintech.core.service.MovieService;
import org.fintech.store.entity.MovieEntity;
import org.fintech.store.repository.MovieRepository;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
@EnableScheduling

public class MovieSchedulerService {

    private final MovieService movieService;

    private final TmdbClient tmdbClient;

    private final MovieRepository movieRepository;

    @Scheduled(cron = "${scheduled.task.update-db}")
    public void loadNewMovies() {
        long id = movieRepository.findTopByOrderByTmdbIdDesc()
                .map(MovieEntity::getTmdbId)
                .orElse(1L);

        long latestId = tmdbClient.getLatestMovie().block().getId();

        List<CompletableFuture<Movie>> futures = new ArrayList<>();

        for (long currentId = id; currentId < latestId; currentId++) {
            futures.add(saveMovieFuture(currentId));
        }

        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();
    }

    protected CompletableFuture<Movie> saveMovieFuture(long movieId) {
        return Mono.fromCallable(() -> tmdbClient.getMovie(movieId).block())
                .doOnSuccess(movie -> movieService.saveTmdbMovie(movie))
                .toFuture();
    }
}
