package org.fintech.core.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.fintech.api.model.CreateMovieRequest;
import org.fintech.api.model.MovieDto;
import org.fintech.api.model.UpdateMovieRequest;
import org.fintech.core.exception.ErrorCode;
import org.fintech.core.exception.ServiceException;
import org.fintech.core.mapper.*;
import org.fintech.core.model.Movie;
import org.fintech.store.entity.*;
import org.fintech.store.repository.MovieRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class MovieService {

    private final MongoTemplate mongoTemplate;

    private final MovieMapper movieMapper;

    private final MovieRepository movieRepository;

    private final GenreService genreService;

    private final KeywordService keywordService;

    private final CastService castService;

    private final CrewService crewService;

    private final CrewMapper crewMapper;

    private final CastMapper castMapper;

    private final KeywordMapper keywordMapper;

    public void create(CreateMovieRequest createMovieRequest) {
        MovieEntity movieEntity = movieMapper.mapToEntity(createMovieRequest);

        if (createMovieRequest.getGenres() != null) {
            List<GenreEntity> genres = genreService.saveGenresByName(createMovieRequest.getGenres());
            movieEntity.setGenres(genres);
        }

        if (createMovieRequest.getKeywords() != null) {
            List<KeywordEntity> keywords = keywordService.saveKeywordsByName(createMovieRequest.getKeywords());
            movieEntity.setKeywords(keywords);
        }

        movieRepository.save(movieEntity);
    }

    public MovieDto getMovie(long id, List<String> fields) {

        MovieEntity movieEntity = movieRepository.findById(id).orElseThrow(()->new ServiceException(ErrorCode.NOT_FOUND, "Фильм не найден"));
        MovieDto movieDto = movieMapper.mapToDto(movieEntity);

        if (fields == null) {
            return movieDto;
        }

        if (fields.contains("keywords")) {
            movieDto.setKeywords(movieEntity.getKeywords().stream().map(keywordMapper::mapToDto).toList());
        }
        if (fields.contains("crews")) {
            movieDto.setCrews(movieEntity.getCrews().stream().map(crewMapper::mapToDto).toList());
        }
        if(fields.contains("casts")) {
            movieDto.setCast(movieEntity.getCast().stream().map(castMapper::mapToDto).toList());
        }
        return movieDto;
    }

    public void updateMovie(UpdateMovieRequest updateMovieRequest, long id) {
        MovieEntity movieEntity = movieRepository.findById(id).orElseThrow(()->new ServiceException(ErrorCode.NOT_FOUND, "Фильм не найден"));

        if (updateMovieRequest.getAdult() != null)  movieEntity.setAdult(updateMovieRequest.getAdult());
        if (updateMovieRequest.getBackdropPath() != null)  movieEntity.setBackdropPath(updateMovieRequest.getBackdropPath());
        if (updateMovieRequest.getPosterPath() != null)  movieEntity.setPosterPath(updateMovieRequest.getPosterPath());
        if (updateMovieRequest.getBudget() != null)  movieEntity.setBudget(updateMovieRequest.getBudget());
        if (updateMovieRequest.getOverview() != null)  movieEntity.setOverview(updateMovieRequest.getOverview());
        if (updateMovieRequest.getReleaseDate() != null)  movieEntity.setReleaseDate(updateMovieRequest.getReleaseDate());
        if (updateMovieRequest.getRevenue() != null)  movieEntity.setRevenue(updateMovieRequest.getRevenue());
        if (updateMovieRequest.getRuntime() != null)  movieEntity.setRuntime(updateMovieRequest.getRuntime());
        if (updateMovieRequest.getStatus() != null)  movieEntity.setStatus(updateMovieRequest.getStatus());
        if (updateMovieRequest.getTagline() != null)  movieEntity.setTagline(updateMovieRequest.getTagline());
        if (updateMovieRequest.getTitle() != null)  movieEntity.setTitle(updateMovieRequest.getTitle());
        if (updateMovieRequest.getOriginalTitle() != null)  movieEntity.setOriginalTitle(updateMovieRequest.getOriginalTitle());
        if (updateMovieRequest.getOriginalLanguage() != null)  movieEntity.setOriginalLanguage(updateMovieRequest.getOriginalLanguage());
        if (updateMovieRequest.getOriginCountry() != null)  movieEntity.setOriginCountry(updateMovieRequest.getOriginCountry());

        movieRepository.save(movieEntity);
    }

    public Page<MovieDto> searchMovies(
            Boolean adult,
            LocalDate releaseDateGte,
            LocalDate releaseDateLte,
            List<String> genresShould,
            List<String> genresMust,
            List<String> genresMustNot,
            Pageable pageable
    ) {

        Criteria criteria = new Criteria();

        if (adult != null) criteria.and("adult").is(adult);
        if (genresShould != null && !genresShould.isEmpty()) criteria.and("genres").in(genreService.getGenresEntityByNames(genresShould));
        if (genresMust!= null && !genresMust.isEmpty()) criteria.and("genres").all(genreService.getGenresEntityByNames(genresMust));
        if (genresMustNot!= null && !genresMustNot.isEmpty()) criteria.and("genres").nin(genreService.getGenresEntityByNames(genresMustNot));
        if (releaseDateGte != null) criteria.and("releaseDate").gte(releaseDateGte);
        if (releaseDateLte != null) criteria.and("releaseDate").lte(releaseDateLte);


        Query query = new Query(criteria);
        query.with(pageable);
        List<MovieDto> movies = mongoTemplate.find(query, MovieEntity.class)
                .stream()
                .map(movieMapper::mapToDto)
                .toList();

        long count = mongoTemplate.count(query, MovieEntity.class);

        return new PageImpl<>(movies, pageable, count);
    }

    @Transactional
    public void saveTmdbMovie(Movie movie){

        if(movieRepository.findByTmdbId(movie.getId()).isPresent()) {
            return;
        }
        MovieEntity movieEntity = movieMapper.mapToEntity(movie);

        genreService.saveGenresEntity(movieEntity.getGenres());

        keywordService.saveKeywordsEntity(movieEntity.getKeywords());

        crewService.saveCrews(movieEntity.getCrews());

        castService.saveCasts(movieEntity.getCast());

        movieRepository.save(movieEntity);
    }

    public void deleteMovie(long id) {
        movieRepository.deleteById(id);
    }
}

