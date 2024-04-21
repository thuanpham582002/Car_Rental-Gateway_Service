package dev.noroom113.car_rent_management_gateway.filter.filter

import dev.noroom113.car_rent_management_gateway.util.JwtUtil
import org.springframework.cloud.gateway.filter.GatewayFilter
import org.springframework.cloud.gateway.filter.GatewayFilterChain
import org.springframework.http.HttpStatus
import org.springframework.http.server.reactive.ServerHttpRequest
import org.springframework.http.server.reactive.ServerHttpResponse
import org.springframework.stereotype.Component
import org.springframework.web.server.ServerWebExchange
import reactor.core.publisher.Mono
import java.util.function.Predicate

@Component
class JwtAuthenticationFilter(private val jwtUtil: JwtUtil) : GatewayFilter {

    override fun filter(exchange: ServerWebExchange, chain: GatewayFilterChain): Mono<Void> {
        val request: ServerHttpRequest = exchange.getRequest()

        val apiEndpoints = listOf("/v1/auth/login", "/v1/auth/register", "/eureka")

        val isApiSecured = Predicate { r: ServerHttpRequest ->
            apiEndpoints.stream()
                .noneMatch { uri: String? -> r.uri.path.contains(uri!!) }
        }

        if (isApiSecured.test(request)) {
            if (authMissing(request)) return onError(exchange)

            var token = request.headers.getOrEmpty("Authorization")[0]

            if (token != null && token.startsWith("Bearer ")) token = token.substring(7)

            try {
                jwtUtil.validateToken(token)
            } catch (e: Exception) {
                return onError(exchange)
            }
        }
        return chain.filter(exchange)
    }

    private fun onError(exchange: ServerWebExchange): Mono<Void> {
        val response: ServerHttpResponse = exchange.getResponse()
        response.setStatusCode(HttpStatus.UNAUTHORIZED)
        return response.setComplete()
    }

    private fun authMissing(request: ServerHttpRequest): Boolean {
        return !request.headers.containsKey("Authorization")
    }
}