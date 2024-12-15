package org.fintech.config.route;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;

public class MovieServiceRouteConfig {

    public static final String MOVIE_SERVICE = "lb://movie-service";

    @Bean
    public RouteLocator movieServiceRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("movie-get", r -> r.path("/movie/**")
                        .and()
                        .method(HttpMethod.GET)
                        .uri(MOVIE_SERVICE))
                .route("auth-sign-in", r -> r.path("/movie/search**")
                        .and()
                        .method(HttpMethod.GET)
                        .uri(MOVIE_SERVICE))
                .build();
    }

}
