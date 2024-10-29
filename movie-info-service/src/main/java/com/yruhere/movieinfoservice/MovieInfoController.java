package com.yruhere.movieinfoservice;

import com.yruhere.movieinfoservice.model.Movie;
import com.yruhere.movieinfoservice.model.Movies;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

@RestController
@RequestMapping("/api/v1/movies")
public class MovieInfoController {

  Logger log = LoggerFactory.getLogger(MovieInfoController.class);

  @Value("${api_key}")
  private String api_key;

  @Autowired
  WebClient.Builder clientBuilder;

  @RequestMapping("/{movieId}")
  public Movie getMovie(@PathVariable("movieId") String movieId) {
    // try {
    //   Thread.sleep(6 * 1000);
    // } catch (Exception e) {
    //   log.error(movieId, e);
    // }
    log.info(
      "****** Request Info for: " + movieId + " with api key: " + api_key
    );

    Movies movies = clientBuilder
      .build()
      .get()
      .uri(
        "https://api.themoviedb.org/3/search/movie?query=" +
        movieId +
        "&api_key=" +
        api_key
      )
      .retrieve()
      .bodyToMono(Movies.class)
      .block();

    return movies.getMovies().get(0);
  }
}
