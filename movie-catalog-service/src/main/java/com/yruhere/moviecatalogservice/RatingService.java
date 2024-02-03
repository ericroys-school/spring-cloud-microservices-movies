package com.yruhere.moviecatalogservice;

import com.yruhere.moviecatalogservice.model.Ratings;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import java.time.Duration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class RatingService {

  @Autowired
  WebClient.Builder clientBuilder;

  @Value("${client.timeout}")
  private int timeout;

  @CircuitBreaker(name = "breaker", fallbackMethod = "getRatingsFallback")
  public Ratings getRating(final String userId) {
    return clientBuilder
      .build()
      .get()
      .uri("http://movie-ratings-service/ratings/" + userId)
      .retrieve()
      .bodyToMono(Ratings.class)
      .timeout(Duration.ofSeconds(timeout))
      .block();
  }

  public Ratings getRatingsFallback() {
    return new Ratings();
  }
}
