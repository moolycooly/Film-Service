package org.fintech.service;

import lombok.RequiredArgsConstructor;
import org.fintech.config.client.WebClientConfiguration;
import org.fintech.model.AuthValidationRequest;
import org.fintech.model.TokenValidationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class JwtService {

    @Qualifier("authServiceClient")
    @Autowired
    private WebClient.Builder webClientBuilder;

    public Mono<TokenValidationResponse> validateToken(String token) {
        return webClientBuilder.build()
                .post()
                .uri("lb://auth-service/auth/token/validate")
                .bodyValue(new AuthValidationRequest(token))
                .retrieve()
                .bodyToMono(TokenValidationResponse.class);
    }

}
