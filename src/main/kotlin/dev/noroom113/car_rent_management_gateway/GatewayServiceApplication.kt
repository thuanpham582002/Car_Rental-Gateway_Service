package dev.noroom113.car_rent_management_gateway

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.cloud.netflix.zuul.EnableZuulProxy

@SpringBootApplication
@EnableZuulProxy
@EnableDiscoveryClient
class CarRentManagementGatewayApplication{

}
// this is test commit message

fun main(args: Array<String>) {
    runApplication<CarRentManagementGatewayApplication>(*args)
}
