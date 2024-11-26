package org.fintech.store.repository;

import org.fintech.store.entity.CastEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CastRepository extends CrudRepository<CastEntity, Long> {

    Optional<CastEntity> findByTmdbCreditId(String id);

    Optional<CastEntity> findByNameAndCharacter(String name, String character);
}
