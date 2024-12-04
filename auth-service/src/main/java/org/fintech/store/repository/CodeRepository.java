package org.fintech.store.repository;

import org.fintech.store.entity.CodeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Optional;

public interface CodeRepository extends JpaRepository<CodeEntity, Long> {

    Optional<CodeEntity> findByEmail(String code);

    void deleteByExpireDateBefore(LocalDateTime expireDate);

}
