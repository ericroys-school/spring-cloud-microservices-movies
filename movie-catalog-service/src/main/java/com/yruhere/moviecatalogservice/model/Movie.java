package com.yruhere.moviecatalogservice.model;

public class Movie {

    private String id;
    private String name;

    public Movie(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public Movie() {
    }

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
