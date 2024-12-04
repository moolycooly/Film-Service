package org.fintech.core.service.impl;

import lombok.RequiredArgsConstructor;
import org.fintech.core.service.CodeGenerator;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;

@Component
@RequiredArgsConstructor
public class BaseCodeGenerator implements CodeGenerator {

    private final SecureRandom secureRandom;

    @Override
    public String generateCode() {
        return String.format("%06d", secureRandom.nextInt(1000000));
    }
}
