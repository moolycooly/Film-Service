package org.fintech.store.repository;

import org.fintech.store.entity.MovieEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<MovieEntity, Integer> {
}
