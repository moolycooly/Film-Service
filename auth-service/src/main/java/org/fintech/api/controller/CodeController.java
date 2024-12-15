package org.fintech.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.fintech.api.ApiPaths;
import org.fintech.api.model.ActivateAccountRequest;
import org.fintech.core.service.CodeService;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Коды")
@RestController
@RequiredArgsConstructor
public class CodeController {

    private final CodeService codeService;

    @Operation(summary = "Запросить код для подтверждения почты")
    @GetMapping(ApiPaths.ACTIVATE_ACCOUNT)
    public void sendConfirmCode(@RequestParam String email) {
        codeService.sendConfirmCode(email);
    }

    @Operation(summary = "Активация аккаунта по почте и коду")
    @PostMapping(ApiPaths.ACTIVATE_ACCOUNT)
    public void activateAccount(@RequestBody @Valid ActivateAccountRequest request) {
        codeService.activateUserAccount(request.getEmail(),request.getCode());
    }
}
