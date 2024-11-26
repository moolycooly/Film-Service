package org.fintech.core.mapper;

import org.fintech.api.model.GenreDto;
import org.fintech.core.model.Genre;
import org.fintech.store.entity.GenreEntity;
import org.springframework.stereotype.Component;

@Component
public class GenreMapper{

    public GenreEntity mapToEntity(Genre genre){
        return GenreEntity.builder()
                .name(genre.getName())
                .tmdbId(genre.getId())
                .build();
    }

    public GenreDto mapToDto(GenreEntity genreEntity) {
        return GenreDto.builder()
                .id(genreEntity.getId())
                .name(genreEntity.getName())
                .tmdbId(genreEntity.getTmdbId())
                .build();
    }

    public GenreEntity mapToEntity(GenreDto genreDto) {
        return GenreEntity.builder()
                .id(genreDto.getId())
                .name(genreDto.getName())
                .tmdbId(genreDto.getTmdbId())
                .build();
    }

}
