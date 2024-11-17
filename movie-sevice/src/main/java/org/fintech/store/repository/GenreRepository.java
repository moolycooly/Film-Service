package org.fintech.store.repository;

import org.fintech.store.entity.GenreEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<GenreEntity, Integer> {
}
