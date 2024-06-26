package dev.noroom113.car_rent_management_gateway

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@SpringBootApplication
@EnableFeignClients
@EnableScheduling
class CarRentManagementGatewayApplication

fun main(args: Array<String>) {
    runApplication<CarRentManagementGatewayApplication>(*args)
}


@RestController
class HealthCheckController {
    @GetMapping("/health")
    fun healthCheck(): String {
        return "Gateway Service is up and running"
    }
}