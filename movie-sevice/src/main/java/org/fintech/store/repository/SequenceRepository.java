package org.fintech.store.repository;

import org.fintech.store.entity.SequenceEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SequenceRepository extends MongoRepository<SequenceEntity, String> {
}
