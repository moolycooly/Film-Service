package org.fintech.core.service;

import lombok.RequiredArgsConstructor;
import org.fintech.api.model.JwtAuthResponse;
import org.fintech.api.model.RegistrationRequest;
import org.fintech.api.model.SignInRequest;
import org.fintech.api.model.User;
import org.fintech.core.client.UserServiceClient;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final JwtService jwtService;

    private final UserServiceClient userServiceClient;

    public JwtAuthResponse signUp(RegistrationRequest registrationRequest) {
        try {
            User user = userServiceClient.registerUser(registrationRequest);
            String accessToken = jwtService.generate(user.getId(), user.getRoles(), "ACCESS");
            String refreshToken = jwtService.generate(user.getId(), user.getRoles(), "REFRESH");
            return new JwtAuthResponse(accessToken, refreshToken);
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while signing up " + e.getLocalizedMessage());
        }

    }

    public JwtAuthResponse signIn(SignInRequest request) {
        try {
            User user = userServiceClient.checkUser(request);
            String accessToken = jwtService.generate(user.getId(), user.getRoles(), "ACCESS");
            String refreshToken = jwtService.generate(user.getId(), user.getRoles(), "REFRESH");
            return new JwtAuthResponse(accessToken, refreshToken);
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while signing in " + e.getLocalizedMessage());
        }
    }

}