package org.fintech.core.mapper;

import info.movito.themoviedbapi.model.core.Movie;
import org.fintech.store.entity.MovieEntity;
import org.springframework.stereotype.Component;

@Component
public class MovieMapper {

    public MovieEntity map(Movie movie) {
        return MovieEntity.builder()
                .id(movie.getId())
                .adult(movie.getAdult())
                .backdropPath(movie.getBackdropPath())
                .title(movie.getTitle())
                .video(movie.getVideo())
                .originalLanguage(movie.getOriginalLanguage())
                .originalTitle(movie.getOriginalTitle())
                .releaseDate(movie.getReleaseDate())
                .overview(movie.getOverview())
                .popularity(movie.getPopularity())
                .posterPath(movie.getPosterPath())
                .voteAverage(movie.getVoteAverage())
                .voteCount(movie.getVoteCount())
                .build();
    }
}
