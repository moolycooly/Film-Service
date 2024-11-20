package org.fintech.store.repository;

import org.fintech.store.entity.MovieIndex;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieIndexRepository extends ElasticsearchRepository<MovieIndex, Integer> {

    @Query("""
        {
          "bool": {
            "must": [
              {
                "match": {
                  "overview": "?0"
                }
              },
              {
                "terms": {
                  "genreIds": ?1
                }
              }
            ],
            "must_not": [
              {
                "term": {
                  "id": "?2"
                }
              }
            ]
          }
        }
    """)
    Page<MovieIndex> findByOverviewAndGenreIds(String overview,
                                               List<Integer> genreIds,
                                               Integer excludeId,
                                               Pageable pageable);

}
