package org.fintech.config.route;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AuthServiceRouteConfig {

    public static final String AUTH_SERVICE = "lb://auth-service";

    @Bean
    public RouteLocator authServiceRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("auth-sign-up", r -> r.path("/auth/sign-up")
                        .uri(AUTH_SERVICE))
                .route("auth-sign-in", r -> r.path("/auth/sign-in")
                        .uri(AUTH_SERVICE))
                .route("auth-activate", r -> r.path("/auth/activate")
                        .uri(AUTH_SERVICE))
                .route("auth-token-validate", r -> r.path("/auth/token/validate")
                        .uri(AUTH_SERVICE))
                .build();
    }
}
