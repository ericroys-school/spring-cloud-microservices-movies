package com.yruhere.movieinfoservice.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import java.util.List;

public class Movies {

  @JsonAlias({ "results" })
  private List<Movie> movies;

  public Movies() {}

  public List<Movie> getMovies() {
    return movies;
  }

  public void setMovies(List<Movie> movies) {
    this.movies = movies;
  }
}
