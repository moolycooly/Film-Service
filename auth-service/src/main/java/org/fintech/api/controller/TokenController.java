package org.fintech.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.fintech.api.ApiPaths;
import org.fintech.api.model.AuthValidationRequest;
import org.fintech.api.model.TokenValidationResponse;
import org.fintech.core.service.AuthenticationService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@Tag(name = "Токены")
@Validated
@RestController
@RequiredArgsConstructor
@Slf4j
public class TokenController {

    private final AuthenticationService authenticationService;

    @PostMapping(ApiPaths.TOKEN_VALIDATE)
    @Operation(description = "Валидация токена")
    public TokenValidationResponse validateToken(@Valid @RequestBody AuthValidationRequest token) {
        return authenticationService.getAuthorities(token.jwtToken());
    }

}
