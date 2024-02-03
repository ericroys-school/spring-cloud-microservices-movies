package com.yruhere.moviecatalogservice;

import com.yruhere.moviecatalogservice.model.Movie;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import java.time.Duration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class MovieInfoService {

  @Value("${client.timeout}")
  private int timeout;

  @Autowired
  WebClient.Builder clientBuilder;

  @CircuitBreaker(name = "breaker", fallbackMethod = "getMovieInfoFallback")
  public Movie getMovieInfo(final String id) {
    return clientBuilder
      .build()
      .get()
      .uri("http://movie-info-service/movies/" + id)
      .retrieve()
      .bodyToMono(Movie.class)
      .timeout(Duration.ofSeconds(timeout))
      .block();
  }

  public Movie getMovieInfoFallback(Exception ex) {
    return new Movie();
  }
}
