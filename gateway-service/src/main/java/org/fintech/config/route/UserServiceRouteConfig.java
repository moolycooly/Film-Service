package org.fintech.config.route;

import lombok.RequiredArgsConstructor;
import org.fintech.config.filter.ProfileByIdFilter;
import org.fintech.config.filter.ProfileByIdPhotoFilter;
import org.fintech.config.filter.UserByIdFilter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;

@Configuration
@RequiredArgsConstructor
public class UserServiceRouteConfig {

    public static final String USER_SERVICE = "lb://user-service";

    private final ProfileByIdFilter profileByIdFilter;

    private final ProfileByIdPhotoFilter profileByIdPhotoFilter;

    private final UserByIdFilter userByIdFilter;

    @Bean
    public RouteLocator userServiceRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("profile-by-id", r -> r.path("/profile")
                        .filters(f -> f.filter((exchange, chain) -> {
                            HttpMethod method = exchange.getRequest().getMethod();
                            if (method == HttpMethod.POST || method == HttpMethod.PATCH || method == HttpMethod.PUT || method == HttpMethod.DELETE) {
                                return profileByIdFilter.filter(exchange, chain);
                            }
                            return chain.filter(exchange);
                        }))
                        .uri(USER_SERVICE))
                .route("user-by-id", r -> r.path("/user")
                        .and()
                        .method(HttpMethod.PUT,HttpMethod.PATCH)
                        .filters(f -> f.filter(userByIdFilter))
                        .uri(USER_SERVICE))
                .route("profile-by-id-photo", r -> r.path("/profile/{id}/photo")
                        .filters(f -> f.filter((exchange, chain) -> {
                            HttpMethod method = exchange.getRequest().getMethod();
                            if (method == HttpMethod.POST || method == HttpMethod.PATCH || method == HttpMethod.PUT) {
                                return profileByIdPhotoFilter.filter(exchange, chain);
                            }
                            return chain.filter(exchange);
                        }))
                        .uri(USER_SERVICE))
                .build();
    }

}
