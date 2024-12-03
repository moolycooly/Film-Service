package org.fintech.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.fintech.api.ApiPaths;
import org.fintech.api.model.JwtAuthResponse;
import org.fintech.api.model.RegistrationRequest;
import org.fintech.api.model.SignInRequest;
import org.fintech.core.service.AuthenticationService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Аутентификация")
@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationService authenticationService;

    @Operation(summary = "Регистрация пользователя")
    @PostMapping(ApiPaths.REGISTRATION)
    public JwtAuthResponse signUp(@RequestBody @Valid RegistrationRequest registrationRequest) {
        return authenticationService.signUp(registrationRequest);
    }

    @Operation(summary = "Авторизация пользователя")
    @PostMapping(ApiPaths.AUTHORIZATION)
    public JwtAuthResponse signIn(@RequestBody @Valid SignInRequest request) {
        return authenticationService.signIn(request);
    }
}
