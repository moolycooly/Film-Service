package org.fintech.core.mapper;

import lombok.RequiredArgsConstructor;
import org.fintech.api.model.CrewDto;
import org.fintech.config.tmdb.TmdbProperties;
import org.fintech.core.model.Crew;
import org.fintech.store.entity.CrewEntity;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CrewMapper {

    private final TmdbProperties tmdbProperties;

    public CrewEntity mapToEntity(Crew crew) {
        return CrewEntity.builder()
                .adult(crew.getAdult())
                .name(crew.getName())
                .gender(crew.getGender())
                .originalName(crew.getOriginalName())
                .profilePath(tmdbProperties.getTmdbImageUrl() + " " + crew.getProfilePath())
                .job(crew.getJob())
                .department(crew.getDepartment())
                .build();
    }

    public CrewEntity mapToEntity(CrewDto crew) {
        return CrewEntity.builder()
                .adult(crew.getAdult())
                .name(crew.getName())
                .gender(crew.getGender())
                .originalName(crew.getOriginalName())
                .profilePath(crew.getProfilePath())
                .job(crew.getJob())
                .department(crew.getDepartment())
                .build();
    }

    public CrewDto mapToDto(CrewEntity crewEntity) {
        return CrewDto.builder()
                .id(crewEntity.getId())
                .name(crewEntity.getName())
                .gender(crewEntity.getGender())
                .job(crewEntity.getJob())
                .originalName(crewEntity.getOriginalName())
                .department(crewEntity.getDepartment())
                .profilePath(crewEntity.getProfilePath())
                .adult(crewEntity.getAdult())
                .build();
    }

}
