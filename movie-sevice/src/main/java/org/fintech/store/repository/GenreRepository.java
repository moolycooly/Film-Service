package org.fintech.store.repository;

import org.fintech.store.entity.GenreEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface GenreRepository extends MongoRepository<GenreEntity, Long> {

    Optional<GenreEntity> findByName(String name);

    List<GenreEntity> findByNameIn(List<String> names);

}
