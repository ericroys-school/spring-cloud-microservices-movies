package com.yruhere.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GatewayApplication {

  public static void main(String[] args) {
    SpringApplication.run(GatewayApplication.class, args);
  }

  @Bean
  public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
    return builder
      .routes()
      .route(
        "movie-catalog-service",
        r ->
          r.path("/api/v1/catalog/*").uri("http://movie-catalog-service:9001")
      )
      .route(
        "movie-info-service",
        r -> r.path("/api/v1/movies/*").uri("http://movie-info-service:9002")
      )
      .route(
        "movie-ratings-service",
        r -> r.path(("/api/v1/ratings/*")).uri("http://order-service:9003")
      )
      .build();
  }
}
