package org.fintech.store.repository;

import org.fintech.store.entity.KeywordEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface KeywordRepository extends MongoRepository<KeywordEntity, Long> {

    Optional<KeywordEntity> findByName(String name);
}
