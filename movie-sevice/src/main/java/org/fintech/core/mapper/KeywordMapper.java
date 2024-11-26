package org.fintech.core.mapper;

import lombok.Builder;
import org.fintech.api.model.GenreDto;
import org.fintech.api.model.KeywordDto;
import org.fintech.core.model.Keyword;
import org.fintech.store.entity.GenreEntity;
import org.fintech.store.entity.KeywordEntity;
import org.springframework.stereotype.Component;

@Component
@Builder
public class KeywordMapper {

    public KeywordEntity mapToEntity(Keyword keyword){
        return KeywordEntity.builder()
                .name(keyword.getName())
                .tmdbId(keyword.getId())
                .build();
    }

    public KeywordDto mapToDto(KeywordEntity keywordEntity) {
        return KeywordDto.builder()
                .id(keywordEntity.getId())
                .name(keywordEntity.getName())
                .tmdbId(keywordEntity.getTmdbId())
                .build();
    }

}
