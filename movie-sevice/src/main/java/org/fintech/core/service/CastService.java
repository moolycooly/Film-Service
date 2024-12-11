package org.fintech.core.service;

import lombok.RequiredArgsConstructor;
import org.fintech.store.entity.CastEntity;
import org.fintech.store.repository.CastRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CastService {

    private final CastRepository castRepository;

    public void saveCast(CastEntity cast) {
        Optional<CastEntity> optionalCastEntity = castRepository.findByNameAndCharacter(cast.getName(),cast.getCharacter());
        if (optionalCastEntity.isPresent()) {
            cast.setId(optionalCastEntity.get().getId());
        }
        else {
            castRepository.save(cast);
        }
    }

    @Transactional
    public void saveCasts(List<CastEntity> cast) {
        cast.forEach(this::saveCast);
    }
}
