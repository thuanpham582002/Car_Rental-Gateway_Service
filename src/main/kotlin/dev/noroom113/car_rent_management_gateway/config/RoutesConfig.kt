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
    val CAR_SERVICE_URL : String = environment.getProperty("CAR_SERVICE_URL") ?: "http://localhost:8081"

    @Bean
    fun routeLocator(builder: RouteLocatorBuilder) = builder.routes {
        println("CAR_SERVICE_URL: $CAR_SERVICE_URL")
        route {
            path("/api/v1/car/**")
            uri(CAR_SERVICE_URL)
        }
    }
}