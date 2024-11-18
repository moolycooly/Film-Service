package org.fintech.store.repository;

import org.fintech.store.entity.GenreEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GenreRepository extends JpaRepository<GenreEntity, Integer> {
    List<GenreEntity> findByTmdbIdIn(List<Integer> tmdbIds);
}
