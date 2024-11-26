package org.fintech.core.service;

import lombok.RequiredArgsConstructor;
import org.fintech.core.mapper.CrewMapper;
import org.fintech.store.entity.CrewEntity;
import org.fintech.store.repository.CrewRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CrewService {

    private final CrewRepository crewRepository;

    private final CrewMapper crewMapper;

    public void saveCrew(CrewEntity crew) {
        Optional<CrewEntity> optionalCrewEntity = crewRepository.findByNameAndJob(crew.getName(),crew.getJob());
        if (optionalCrewEntity.isPresent()) {
            crew.setId(optionalCrewEntity.get().getId());
        }
        else {
            crewRepository.save(crew);
        }
    }

    public void saveCrews(List<CrewEntity> crews) {
        crews.forEach(this::saveCrew);
    }
}
