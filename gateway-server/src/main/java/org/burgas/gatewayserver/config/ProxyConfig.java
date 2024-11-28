package org.burgas.gatewayserver.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProxyConfig {

    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(
                        "districts",
                        predicateSpec -> predicateSpec
                                .path("/districts/**", "/farmers/**",
                                        "/farming-district/v3/api-docs")
                                .uri("http://localhost:9000")
                )
                .build();
    }
}
