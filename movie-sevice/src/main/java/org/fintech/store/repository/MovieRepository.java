package org.fintech.store.repository;

import org.fintech.store.entity.MovieEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MovieRepository extends JpaRepository<MovieEntity, Integer> {

    @EntityGraph(attributePaths = {"genres"})
    Page<MovieEntity> findAll(Specification<MovieEntity> specification, Pageable pageable);

}
