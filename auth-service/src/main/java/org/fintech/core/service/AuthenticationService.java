package org.fintech.core.service;

import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.fintech.api.model.*;
import org.fintech.core.client.UserServiceClient;
import org.fintech.core.exception.ErrorCode;
import org.fintech.core.exception.ServiceException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationService {

    private final JwtService jwtService;

    private final UserServiceClient userServiceClient;

    public JwtAuthResponse signUp(RegistrationRequest registrationRequest) {
        User user = userServiceClient.registerUser(registrationRequest);
        String accessToken = jwtService.createToken(user.getId(),Map.of("authorities",user.getRoles()));
        return new JwtAuthResponse(accessToken);
    }

    public JwtAuthResponse signIn(SignInRequest request) {
        User user = userServiceClient.checkUser(request);
        String accessToken = jwtService.createToken(user.getId(),Map.of("authorities",user.getRoles()));
        return new JwtAuthResponse(accessToken);
    }

    public TokenValidationResponse getAuthorities(String token) {
        long userId = jwtService.getUserId(token);
        List<String> authorities = jwtService.extractAuthorities(token);
        return new TokenValidationResponse(userId, authorities);
    }

}