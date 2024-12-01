package org.fintech.core.mapper;

import lombok.RequiredArgsConstructor;
import org.fintech.api.model.CastDto;
import org.fintech.config.tmdb.TmdbProperties;
import org.fintech.core.model.Cast;
import org.fintech.store.entity.CastEntity;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CastMapper {

    private final TmdbProperties tmdbProperties;

    public CastEntity mapToEntity(Cast cast) {
        return CastEntity.builder()
                .adult(cast.getAdult())
                .name(cast.getName())
                .gender(cast.getGender())
                .role(cast.getRole())
                .originalName(cast.getOriginalName())
                .character(cast.getCharacter())
                .profilePath(tmdbProperties.getTmdbImageUrl() + " " + cast.getProfilePath())
                .build();
    }

    public CastEntity mapToEntity(CastDto cast) {
        return CastEntity.builder()
                .adult(cast.getAdult())
                .name(cast.getName())
                .gender(cast.getGender())
                .role(cast.getRole())
                .originalName(cast.getOriginalName())
                .character(cast.getCharacter())
                .profilePath(cast.getProfilePath())
                .build();
    }

    public CastDto mapToDto(CastEntity castEntity) {
        return CastDto.builder()
                .id(castEntity.getId())
                .name(castEntity.getName())
                .gender(castEntity.getGender())
                .role(castEntity.getRole())
                .originalName(castEntity.getOriginalName())
                .character(castEntity.getCharacter())
                .profilePath(castEntity.getProfilePath())
                .adult(castEntity.getAdult())
                .build();
    }

}
