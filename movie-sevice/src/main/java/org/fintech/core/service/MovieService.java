package org.fintech.core.service;


import co.elastic.clients.elasticsearch._types.FieldValue;


import co.elastic.clients.elasticsearch._types.query_dsl.*;
import co.elastic.clients.json.JsonData;
import info.movito.themoviedbapi.TmdbApi;
import info.movito.themoviedbapi.model.core.Movie;
import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;

import org.fintech.api.model.FilterCondition;
import org.fintech.core.mapper.MovieMapper;
import org.fintech.store.entity.GenreEntity;
import org.fintech.store.entity.MovieIndex;
import org.fintech.store.entity.MovieEntity;
import org.fintech.store.repository.GenreRepository;
import org.fintech.store.repository.MovieIndexRepository;
import org.fintech.store.repository.MovieRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.client.elc.NativeQueryBuilder;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.stereotype.Service;



import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class MovieService {

    private final MovieRepository movieRepository;

    private final GenreRepository genreRepository;

    private final MovieIndexRepository movieIndexRepository;

    private final ElasticsearchOperations elasticsearchOperations;

    private final MovieMapper movieMapper;

    public Page<MovieIndex> findMoviesByFilters(int page, int size, List<FilterCondition> filterConditions) {
        BoolQuery.Builder boolQueryBuilder = QueryBuilders.bool();
        for (FilterCondition filterCondition : filterConditions) {
            if(filterCondition.getMinValue()!=null){
                var rangeBuilder = new RangeQuery.Builder().field(filterCondition.getField()).gte(JsonData.of(filterCondition.getMinValue()));
                boolQueryBuilder.filter(rangeBuilder.build()._toQuery());
            }
            if(filterCondition.getMaxValue()!=null) {
                var rangeBuilder = new RangeQuery.Builder().field(filterCondition.getField()).lte(JsonData.of(filterCondition.getMaxValue()));
                boolQueryBuilder.filter(rangeBuilder.build()._toQuery());
            }

            if(filterCondition.getMustNot() != null) {
                TermsQueryField countryTerms = new TermsQueryField.Builder()
                        .value(filterCondition.getMustNot().stream().map(FieldValue::of).toList())
                        .build();
                boolQueryBuilder.mustNot(QueryBuilders.terms()
                        .field(filterCondition.getField())
                        .terms(countryTerms)
                        .build()._toQuery()
                );
            }
            if(filterCondition.getMustBe()!= null) {
                TermsQueryField countryTerms = new TermsQueryField.Builder()
                        .value(filterCondition.getMustBe().stream().map(FieldValue::of).toList())
                        .build();
                boolQueryBuilder.must(QueryBuilders.terms()
                        .field(filterCondition.getField())
                        .terms(countryTerms)
                        .build()._toQuery()
                );
            }
            if(filterCondition.getShould() != null) {
                TermsQueryField countryTerms = new TermsQueryField.Builder()
                        .value(filterCondition.getShould().stream().map(FieldValue::of).toList())
                        .build();
                boolQueryBuilder.should(QueryBuilders.terms()
                        .field(filterCondition.getField())
                        .terms(countryTerms)
                        .build()._toQuery()
                        )
                .minimumShouldMatch("1");
            }
            if(filterCondition.getMatch() != null) {
                MatchQuery matchQuery = new MatchQuery.Builder()
                        .field(filterCondition.getField())
                        .query(FieldValue.of(filterCondition.getMatch()))
                        .analyzer("russian")
                        .build();
                boolQueryBuilder.must(matchQuery._toQuery());
            }
        }

        Pageable pageable = PageRequest.of(page, size);
        Query searchQuery = new NativeQueryBuilder()
                .withQuery(boolQueryBuilder.build()._toQuery())
                .withPageable(pageable)
                .build();

        log.error(searchQuery.toString());


        SearchHits<MovieIndex> searchHits = elasticsearchOperations.search(searchQuery, MovieIndex.class);
        return new PageImpl<>(searchHits.stream().map(SearchHit::getContent).collect(Collectors.toList()), pageable, searchHits.getTotalHits());

    }

    public void saveAllMovies(List<Movie> movieList) {
        List<MovieEntity> movieEntities = this.saveMovies(movieList);
        this.saveMoviesDocuments(movieList, movieEntities.stream().map(MovieEntity::getId).toList());
    }

    private List<MovieEntity> saveMovies(List<Movie> movieList) {
        List<Integer> allGenreIds = movieList.stream()
                .flatMap(movie -> movie.getGenreIds().stream())
                .toList();
        List<GenreEntity> genres = genreRepository.findByTmdbIdIn(allGenreIds);
        List<MovieEntity> movieEntities = new ArrayList<>();
        for (Movie movie : movieList) {
            Set<GenreEntity> movieGenres = genres.stream()
                    .filter(genre -> movie.getGenreIds().contains(genre.getTmdbId()))
                    .collect(Collectors.toSet());
            MovieEntity movieEntity = movieMapper.mapToEntity(movie);
            movieEntity.setGenres(movieGenres);
            movieEntities.add(movieEntity);
        }
       return movieRepository.saveAll(movieEntities);
    }

    private void saveMoviesDocuments(List<Movie> movieList, List<Integer> id) {
        if(movieList.size() != id.size()) {
            throw new IllegalArgumentException("Movie list size does not match id list size");
        }
        for(int i = 0; i < movieList.size(); i++) {
            movieIndexRepository.save(movieMapper.mapToDocumet(movieList.get(i),id.get(i)));
        }
    }

}

