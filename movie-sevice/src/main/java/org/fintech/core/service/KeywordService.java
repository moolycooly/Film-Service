package org.fintech.core.service;

import lombok.RequiredArgsConstructor;
import org.fintech.store.entity.GenreEntity;
import org.fintech.store.entity.KeywordEntity;
import org.fintech.store.repository.KeywordRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Key;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class KeywordService {

    private final KeywordRepository keywordRepository;

    public void saveKeyword(KeywordEntity keyword) {
        Optional<KeywordEntity> optionalKeywordEntity = keywordRepository.findByName(keyword.getName());
        if (optionalKeywordEntity.isPresent()) {
            keyword.setId(optionalKeywordEntity.get().getId());
        }
        else {
            keywordRepository.save(keyword);
        }
    }

    @Transactional
    public void saveKeywordsEntity(List<KeywordEntity> keywords) {
        keywords.forEach(this::saveKeyword);
    }

    public KeywordEntity saveKeywordByName(String name) {
        Optional<KeywordEntity> optionalKeywordEntity = keywordRepository.findByName(name);
        if (optionalKeywordEntity.isPresent()) {
            return optionalKeywordEntity.get();
        }
        else {
            KeywordEntity keyword = new KeywordEntity();
            keyword.setName(name);
            keywordRepository.save(keyword);
            return keyword;
        }
    }

    public List<KeywordEntity> saveKeywordsByName(List<String> names) {
        return names.stream().map(this::saveKeywordByName).toList();
    }
}
