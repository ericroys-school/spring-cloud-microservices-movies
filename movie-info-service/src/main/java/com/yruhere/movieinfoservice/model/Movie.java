package com.yruhere.movieinfoservice.model;

import com.fasterxml.jackson.annotation.JsonAlias;

public class Movie {

  private String id;

  @JsonAlias({ "original_title" })
  private String name;

  public Movie(String id, String name) {
    this.id = id;
    this.name = name;
  }

  public Movie() {}

  public String getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public void setId(String id) {
    this.id = id;
  }

  public void setName(String name) {
    this.name = name;
  }
}
