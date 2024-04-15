package dev.noroom113.car_rent_management_gateway

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.cloud.netflix.zuul.EnableZuulProxy
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@SpringBootApplication
@EnableZuulProxy
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