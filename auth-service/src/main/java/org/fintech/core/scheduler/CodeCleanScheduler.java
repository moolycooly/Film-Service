package org.fintech.core.scheduler;

import jakarta.validation.ClockProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.fintech.store.repository.CodeRepository;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class CodeCleanScheduler {

    private final CodeRepository codeRepository;

    private final ClockProvider clockProvider;

    @Transactional
    @Scheduled(cron = "${scheduler-timeout.clear-old-coles}")
    public void cleanOldCodes() {
        log.error("enabled clean");
        codeRepository.deleteByExpireDateBefore(LocalDateTime.now(clockProvider.getClock()));
    }

}
