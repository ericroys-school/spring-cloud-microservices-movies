package com.yruhere.moviecatalogservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
@EnableDiscoveryClient
@RequestMapping("/catalog")
public class MovieCatalogServiceApplication {

  @Bean
  @LoadBalanced
  public WebClient.Builder getWebClient() {
    return WebClient.builder();
  }

  public static void main(String[] args) {
    SpringApplication.run(MovieCatalogServiceApplication.class, args);
  }
}
