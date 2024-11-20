package org.fintech.core.service;

import lombok.RequiredArgsConstructor;
import org.fintech.api.exception.GenreNotFoundException;
import org.fintech.store.entity.GenreEntity;
import org.fintech.store.repository.GenreRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GenreService {

    private final GenreRepository genreRepository;

    public List<String> mapIdsToGenresName(List<Integer> genreIds) {
        return genreRepository.findByIdIn(genreIds).stream().map(GenreEntity::getName).toList();
    }
}
