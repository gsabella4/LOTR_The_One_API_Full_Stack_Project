package com.lotr_project.Models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Movie {

    //attributes
    private int movieId;
    @JsonProperty("_id")
    private String theOneMovieId;
    private String name;
    private int runtimeInMinutes;
    private int budgetInMillions;

    // Review session w/ Andy 2.28
    // New Functionality ---
    // mgmt wants to know cost per minute for a movie (budget/runTimeInMin)
    // unit testing for this method

    public double getCostPerMinute() {
        return (budgetInMillions * 1.0) / runtimeInMinutes;
    }

    //getters and setters
    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public String getTheOneMovieId() {
        return theOneMovieId;
    }

    public void setTheOneMovieId(String theOneMovieId) {
        this.theOneMovieId = theOneMovieId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRuntimeInMinutes() {
        return runtimeInMinutes;
    }

    public void setRuntimeInMinutes(int runTimeInMinutes) {
        this.runtimeInMinutes = runTimeInMinutes;
    }

    public int getBudgetInMillions() {
        return budgetInMillions;
    }

    public void setBudgetInMillions(int budgetInMillions) {
        this.budgetInMillions = budgetInMillions;
    }

    //toString
    @Override
    public String toString() {
        return "Movie{" +
                "movieId=" + movieId +
                ", theOneMovieId='" + theOneMovieId + '\'' +
                ", name='" + name + '\'' +
                ", runTimeInMinutes=" + runtimeInMinutes +
                ", budgetInMillions=" + budgetInMillions +
                '}';
    }
}
