package org.fintech.store.repository;

import org.fintech.store.entity.MovieIndex;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieIndexRepository extends ElasticsearchRepository<MovieIndex, Integer> {
}
