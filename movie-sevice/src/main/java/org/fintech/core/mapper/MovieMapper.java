package org.fintech.core.mapper;

import lombok.RequiredArgsConstructor;
import org.fintech.api.model.CreateMovieRequest;
import org.fintech.api.model.MovieDto;
import org.fintech.config.tmdb.TmdbProperties;
import org.fintech.core.model.Movie;
import org.fintech.store.entity.MovieEntity;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MovieMapper {

    private final GenreMapper genreMapper;

    private final KeywordMapper keywordMapper;

    private final CastMapper castMapper;

    private final CrewMapper crewMapper;

    private final TmdbProperties tmdbProperties;

    public MovieEntity mapToEntity(Movie movie) {
        return MovieEntity.builder()
                .tmdbId(movie.getId())
                .adult(movie.getAdult())
                .backdropPath(tmdbProperties.getTmdbImageUrl() + movie.getBackdropPath())
                .title(movie.getTitle())
                .video(movie.getVideo())
                .originalLanguage(movie.getOriginalLanguage())
                .originalTitle(movie.getOriginalTitle())
                .overview(movie.getOverview())
                .posterPath(tmdbProperties.getTmdbImageUrl() + movie.getTmdbPosterPath())
                .tmdbVoteAverage(movie.getTmdbVoteAverage())
                .releaseDate(movie.getReleaseDate())
                .originCountry(movie.getOriginCountry())
                .revenue(movie.getRevenue())
                .budget(movie.getBudget())
                .runtime(movie.getRuntime())
                .tagline(movie.getTagline())
                .keywords(movie.getKeywords().getKeywords().stream().map(keywordMapper::mapToEntity).toList())
                .genres(movie.getGenres().stream().map(genreMapper::mapToEntity).toList())
                .crews(movie.getCredits().getCrew().stream().map(crewMapper::mapToEntity).toList())
                .cast(movie.getCredits().getCast().stream().map(castMapper::mapToEntity).toList())
                .build();
    }

    public MovieDto mapToDto(MovieEntity movieEntity) {
        return MovieDto.builder()
                .id(movieEntity.getId())
                .adult(movieEntity.getAdult())
                .backdropPath(movieEntity.getBackdropPath())
                .budget(movieEntity.getBudget())
                .originalLanguage(movieEntity.getOriginalLanguage())
                .originalTitle(movieEntity.getOriginalTitle())
                .overview(movieEntity.getOverview())
                .tmdbVoteAverage(movieEntity.getTmdbVoteAverage())
                .releaseDate(movieEntity.getReleaseDate())
                .tagline(movieEntity.getTagline())
                .status(movieEntity.getStatus())
                .originCountry(movieEntity.getOriginCountry())
                .revenue(movieEntity.getRevenue())
                .runtime(movieEntity.getRuntime())
                .posterPath(movieEntity.getPosterPath())
                .video(movieEntity.getVideo())
                .genres(movieEntity.getGenres().stream().map(genreMapper::mapToDto).toList())
                .build();
    }

    public MovieEntity mapToEntity(MovieDto movieDto) {
        return MovieEntity.builder()
                .id(movieDto.getId())
                .adult(movieDto.getAdult())
                .backdropPath(movieDto.getBackdropPath())
                .budget(movieDto.getBudget())
                .originalLanguage(movieDto.getOriginalLanguage())
                .originalTitle(movieDto.getOriginalTitle())
                .overview(movieDto.getOverview())
                .tmdbVoteAverage(movieDto.getTmdbVoteAverage())
                .releaseDate(movieDto.getReleaseDate())
                .tagline(movieDto.getTagline())
                .status(movieDto.getStatus())
                .originCountry(movieDto.getOriginCountry())
                .revenue(movieDto.getRevenue())
                .runtime(movieDto.getRuntime())
                .posterPath(movieDto.getPosterPath())
                .video(movieDto.getVideo())
                .build();
    }

    public MovieEntity mapToEntity(CreateMovieRequest movie) {
        return MovieEntity.builder()
                .adult(movie.getAdult())
                .backdropPath(movie.getBackdropPath())
                .budget(movie.getBudget())
                .originalLanguage(movie.getOriginalLanguage())
                .originalTitle(movie.getOriginalTitle())
                .overview(movie.getOverview())
                .tmdbVoteAverage(movie.getTmdbVoteAverage())
                .releaseDate(movie.getReleaseDate())
                .tagline(movie.getTagline())
                .status(movie.getStatus())
                .originCountry(movie.getOriginCountry())
                .revenue(movie.getRevenue())
                .runtime(movie.getRuntime())
                .posterPath(movie.getPosterPath())
                .video(movie.getVideo())
                .build();
    }


}
