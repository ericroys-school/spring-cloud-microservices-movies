package com.yruhere.movieratingsservice;

import com.yruhere.movieratingsservice.model.Rating;
import com.yruhere.movieratingsservice.model.Ratings;
import java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ratings")
public class RatingsController {

  Logger log = LoggerFactory.getLogger(RatingsController.class);

  @RequestMapping("/{userId}")
  public Ratings getRating(@PathVariable("userId") String userId) {
    try {
      Thread.sleep(6 * 1000);
    } catch (Exception e) {
      log.error(userId, e);
    }
    // get all rated movie ids
    return new Ratings(
      Arrays.asList(
        new Rating("11", 5),
        new Rating("12", 6),
        new Rating("13", 8)
      )
    );
  }
}
