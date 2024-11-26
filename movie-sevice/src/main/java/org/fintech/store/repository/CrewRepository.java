package org.fintech.store.repository;

import org.fintech.store.entity.CrewEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface CrewRepository extends MongoRepository<CrewEntity, Long> {

    Optional<CrewEntity> findByTmdbCreditId(String id);

    Optional<CrewEntity> findByNameAndJob(String name, String job);

}
