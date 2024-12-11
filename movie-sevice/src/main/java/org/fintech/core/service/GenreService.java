package org.fintech.core.service;

import lombok.RequiredArgsConstructor;
import org.fintech.api.model.GenreDto;
import org.fintech.core.mapper.GenreMapper;
import org.fintech.core.model.Genre;
import org.fintech.store.entity.GenreEntity;
import org.fintech.store.repository.GenreRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GenreService {

    private final GenreRepository genreRepository;

    public void saveGenre(GenreEntity genre) {
        Optional<GenreEntity> optionalGenreEntity = genreRepository.findByName(genre.getName());
        if (optionalGenreEntity.isPresent()) {
            genre.setId(optionalGenreEntity.get().getId());
        }
        else {
            genreRepository.save(genre);
        }
    }

    public GenreEntity saveGenreByName(String name) {
        Optional<GenreEntity> optionalGenreEntity = genreRepository.findByName(name);
        if (optionalGenreEntity.isPresent()) {
            return optionalGenreEntity.get();
        }
        else {
            GenreEntity genre = new GenreEntity();
            genre.setName(name);
            genreRepository.save(genre);
            return genre;
        }
    }

    public List<GenreEntity> saveGenresByName(List<String> names) {
        return names.stream().map(this::saveGenreByName).toList();
    }

    public List<GenreEntity> getGenresEntityByNames(List<String> names) {
        return genreRepository.findByNameIn(names);
    }

    @Transactional
    public void saveGenresEntity(List<GenreEntity> genres) {
        genres.forEach(this::saveGenre);
    }

}
