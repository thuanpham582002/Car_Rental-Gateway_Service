package dev.noroom113.car_rent_management_gateway.config

import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder
import org.springframework.cloud.gateway.route.builder.routes
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.client.WebClient

@Configuration
class RoutesConfig {

    @Bean
    fun routeLocator(builder: RouteLocatorBuilder, webClientBuilder: WebClient.Builder) = builder.routes {
        route("user_service") {
            path("/api/v1/user/**")
            uri("lb://user-service")
        }
        route("car_service") {
            path("/api/v1/car/**")
            uri("lb://car-service")
        }
        route("accessibility_service") {
            path("/api/v1/accessibility/**")
            uri("lb://accessibility-service")
        }
        route("contract_service") {
            path("/api/v1/contract/**")
            uri("lb://contract-service")
        }
        route("auth_service") {
            webClientBuilder.build()
            path("/api/v1/auth/**")
            uri("lb://auth-service")
        }
    }
}