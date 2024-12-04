package org.fintech.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.fintech.api.ApiPaths;
import org.fintech.api.model.ActivateAccountRequest;
import org.fintech.api.model.JwtAuthResponse;
import org.fintech.api.model.RegistrationRequest;
import org.fintech.api.model.SignInRequest;
import org.fintech.core.service.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Аутентификация")
@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationService authenticationService;

    @Operation(summary = "Регистрация пользователя с неактивированным аккаунтом, код отправляется на почту")
    @PostMapping(ApiPaths.REGISTRATION)
    public void signUp(@RequestBody @Valid RegistrationRequest request) {
        authenticationService.signUp(request);
    }

    @Operation(summary = "Авторизация пользователя по username")
    @PostMapping(ApiPaths.AUTHORIZATION)
    public JwtAuthResponse signIn(@RequestBody @Valid SignInRequest request) {
        return authenticationService.signIn(request);
    }

}
