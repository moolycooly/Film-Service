package org.fintech.store.repository;

import org.fintech.store.entity.MovieEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface MovieRepository extends MongoRepository<MovieEntity, Long> {

    Optional<MovieEntity> findByTmdbId(Long id);

    Optional<MovieEntity> findTopByOrderByTmdbIdDesc();

}
