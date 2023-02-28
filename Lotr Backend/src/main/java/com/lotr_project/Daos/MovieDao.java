package com.lotr_project.Daos;

import com.lotr_project.Models.Movie;

import java.util.List;

public interface MovieDao {

    //Read all movies
    List<Movie> getAllMovies();

    //Read movie by movie_id
    Movie getMovieByMovieId(int id);

    //Create movie
    Movie addNewMovie(Movie movie);

    //Delete movie
    void deleteMovie(int id);
}
