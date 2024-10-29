package com.yruhere.moviecatalogservice;

import com.yruhere.moviecatalogservice.model.CatalogItem;
import com.yruhere.moviecatalogservice.model.CatalogItems;
import com.yruhere.moviecatalogservice.model.Movie;
import com.yruhere.moviecatalogservice.model.Ratings;
import java.time.Duration;
import java.util.Date;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

@RestController
@RequestMapping("/api/vi/catalog")
public class MovieCatalogController {

  Logger log = LoggerFactory.getLogger(MovieCatalogController.class);

  // @Value("${client.timeout}")
  // private int timeout = 2;

  // @Autowired
  // WebClient.Builder clientBuilder;

  @Autowired
  MovieInfoService movieService;

  @Autowired
  RatingService ratingService;

  @RequestMapping("/{userId}")
  public CatalogItems getCatalogItems(String userId) {
    Ratings ratings = null;
    try {
      // get all rated movie ids
      ratings = ratingService.getRating(userId);
    } catch (Exception e) {
      return new CatalogItems();
    }
    // for each, get ratings
    try {
      return (ratings == null)
        ? new CatalogItems()
        : new CatalogItems(
          ratings
            .getRatings()
            .stream()
            .map(rating -> {
              Movie movie = movieService.getMovieInfo(rating.getMovieId());
              // aggregate the results
              return new CatalogItem(
                movie.getName(),
                "desc",
                rating.getRating()
              );
            })
            .collect(Collectors.toList())
        );
    } catch (Exception e) {
      return new CatalogItems();
    }
  }
}
