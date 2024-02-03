package com.yruhere.movieinfoservice;

import com.yruhere.movieinfoservice.model.Movie;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

@RestController
@RequestMapping("/movies")
public class MovieInfoController {

  Logger log = LoggerFactory.getLogger(MovieInfoController.class);

  @Value("${api_key}")
  private String api_key;

  @Autowired
  WebClient.Builder clientBuilder;

  @RequestMapping("/{movieId}")
  public Movie getMovie(@PathVariable("movieId") String movieId) {
    try {
      Thread.sleep(6 * 1000);
    } catch (Exception e) {
      log.error(movieId, e);
    }

    Movie movie = clientBuilder
      .build()
      .get()
      .uri(
        "https://api.themoviedb.org/3/movie/" + movieId + "?api_key=" + api_key
      )
      .retrieve()
      .bodyToMono(Movie.class)
      .block();
    return movie;
    // new Movie(movieId, "Tron");
  }
}
