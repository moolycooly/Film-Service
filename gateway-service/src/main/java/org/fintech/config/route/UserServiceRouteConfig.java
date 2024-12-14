package org.fintech.config.route;

import lombok.RequiredArgsConstructor;
import org.fintech.config.filter.ProfileByIdFilter;
import org.fintech.config.filter.UserByIdFilter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class UserServiceRouteConfig {

    private static final String USER_SERVICE = "lb://user-service";

    private final ProfileByIdFilter profileByIdFilter;

    private final UserByIdFilter userByIdFilter;

    @Bean
    public RouteLocator userServiceRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("profile-by-id", r -> r.path("/profile/**")
                        .and().method("PUT", "PATCH")
                        .filters(f -> f.filter(profileByIdFilter))
                        .uri(USER_SERVICE))
                .route("user-by-id", r -> r.path("/user/")
                        .and().method("PUT", "PATCH")
                        .filters(f -> f.filter(userByIdFilter))
                        .uri(USER_SERVICE))
                .route("profile-by-id-photo", r -> r.path("/profile/{id}/photo")
                        .filters(f -> f.filter((exchange, chain) -> {
                            // Extract ID from JWT Token
                            return chain.filter(exchange);
                        }))
                        .uri(USER_SERVICE))
                .build();
    }

}
