package org.fintech.core.service;

import jakarta.validation.ClockProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.fintech.core.client.UserServiceClient;
import org.fintech.core.exception.ErrorCode;
import org.fintech.core.exception.ServiceException;
import org.fintech.core.model.ActivateUser;
import org.fintech.store.entity.CodeEntity;
import org.fintech.store.repository.CodeRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@Service
@RequiredArgsConstructor
public class CodeService {

    private final EmailService emailService;

    private final CodeRepository codeRepository;

    private final UserServiceClient userServiceClient;

    private final CodeGenerator codeGenerator;

    private final ClockProvider clockProvider;

    @Value("${confirm-code.expiration}")
    private Duration duration;

    public void sendConfirmCode(String email) {
        String code = codeGenerator.generateCode();
        CodeEntity codeEntity = codeRepository.findByEmail(email).orElse(new CodeEntity());
        codeEntity.setEmail(email);
        codeEntity.setCode(code);
        codeEntity.setExpireDate(OffsetDateTime.now(clockProvider.getClock()).plusMinutes(duration.toMinutes()).toLocalDateTime());
        codeRepository.save(codeEntity);
        Thread.startVirtualThread(() ->  emailService.sendMessage(email, "Код для подтверждения", code));
    }

    public void activateUserAccount(String email, String code) {
        CodeEntity codeEntity = codeRepository.findByEmail(email)
                .orElseThrow(()->new ServiceException(
                        ErrorCode.NOT_FOUND,String.format("Пользователь с email: %s не найден",email))
                );
        if (validateCode(codeEntity, code)) {
            userServiceClient.activateUser(new ActivateUser(email));
        }
        else {
            throw new ServiceException(ErrorCode.INVALID_ARGUMENT, "Неверный код подтверждения");
        }
    }

    private boolean validateCode(CodeEntity codeEntity, String code) {
        return codeEntity.getCode().equals(code) && codeEntity.getExpireDate().isAfter(LocalDateTime.now(clockProvider.getClock()));
    }
}

