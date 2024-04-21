package dev.noroom113.car_rent_management_gateway.config

import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder
import org.springframework.cloud.gateway.route.builder.routes
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.env.Environment

@Configuration
class RoutesConfig(
   environment: Environment
) {
    val USER_SERVICE_URL : String = environment.getProperty("USER_SERVICE_URL") ?: "http://localhost:8080"
    val CAR_SERVICE_URL : String = environment.getProperty("CAR_SERVICE_URL") ?: "http://localhost:8081"
    val ACCESSIBILITY_SERVICE_URL : String = environment.getProperty("ACCESSIBILITY_SERVICE_URL") ?: "http://localhost:8082"

    @Bean
    fun routeLocator(builder: RouteLocatorBuilder) = builder.routes {
        println("CAR_SERVICE_URL: $CAR_SERVICE_URL")
        route("user_service") {
            path("/api/v1/user/**")
            uri(USER_SERVICE_URL)
        }
        route("car_service") {
            path("/api/v1/car/**")
            uri("lb://car-service")
        }
        route("accessibility_service") {
            path("//api/v1/accessibility/**")
            uri("lb://accessibility-service")
        }
    }
}