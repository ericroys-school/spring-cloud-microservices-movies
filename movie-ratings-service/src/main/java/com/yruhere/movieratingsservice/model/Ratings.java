package com.yruhere.movieratingsservice.model;

import java.util.List;

public class Ratings {

    private List<Rating> ratings;

    public Ratings(List<Rating> ratings) {
        this.ratings = ratings;
    }

    public Ratings() {
    }

    public List<Rating> getRatings() {
        return ratings;
    }

    public void setRatings(List<Rating> ratings) {
        this.ratings = ratings;
    }

}
