package com.aybaroud.gatewayservice;

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.timelimiter.TimeLimiterConfig;
import io.netty.resolver.DefaultAddressResolverGroup;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.circuitbreaker.resilience4j.ReactiveResilience4JCircuitBreakerFactory;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JConfigBuilder;
import org.springframework.cloud.client.circuitbreaker.Customizer;
import org.springframework.cloud.client.discovery.ReactiveDiscoveryClient;
import org.springframework.cloud.gateway.discovery.DiscoveryClientRouteDefinitionLocator;
import org.springframework.cloud.gateway.discovery.DiscoveryLocatorProperties;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.codec.ServerCodecConfigurer;
import reactor.netty.http.client.HttpClient;

import java.time.Duration;

@SpringBootApplication
public class GatewayServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayServiceApplication.class, args);
    }

    @Bean
    RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(p -> p.path("/v3.1/**")
                        .filters(f -> f.circuitBreaker(c -> c.setName("countries")
                                        .setFallbackUri("forward:/defaultCountries")))
                        .uri("http://restcountries.com"))
                .build();
    }

    /** Gateway Routing : Same static configuration as yaml file*/
    /*@Bean
    RouteLocator staticRoutes(RouteLocatorBuilder builder){
        return builder.routes()
                .route(p->p.path("/customers/**")
                        .uri("lb://CUSTOMER-SERVICE/"))
                .route(p->p.path("/products/**")
                        .uri("lb://INVENTORY-SERVICE/"))
                .build();
    }*/

    /** Gateway Routing : dynamic configuration*/
    @Bean
    DiscoveryClientRouteDefinitionLocator dynamicRoutes(ReactiveDiscoveryClient rdc,
                                                        DiscoveryLocatorProperties dlp){
        return new DiscoveryClientRouteDefinitionLocator(rdc,dlp);
    }

    @Bean
    public HttpClient httpClient() {
        return HttpClient.create().
                resolver(DefaultAddressResolverGroup.INSTANCE);
    }

    @Bean
    public Customizer<ReactiveResilience4JCircuitBreakerFactory> defaultCustomizer() {
        return factory -> factory.configureDefault(id -> new Resilience4JConfigBuilder(id)
                .circuitBreakerConfig(CircuitBreakerConfig.ofDefaults())
                .timeLimiterConfig(TimeLimiterConfig.custom().timeoutDuration(Duration.ofMillis(170)).build())
                .build());
    }

}
