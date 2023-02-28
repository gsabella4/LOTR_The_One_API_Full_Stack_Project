package com.lotr_project.Daos;

import com.lotr_project.Models.Movie;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcMovieDao implements MovieDao {

    private JdbcTemplate jdbcTemplate;

    public JdbcMovieDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Movie> getAllMovies() {
        String sql = "SELECT movie_id, the_one_movie_id, name, " +
                    "runtime_in_minutes, budget_in_millions " +
                    "FROM movie;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        List<Movie> movieList = new ArrayList<>();
        while(results.next()){
            Movie movie = mapRowToMovie(results);
            movieList.add(movie);
        }
        return movieList;
    }

    @Override
    public Movie getMovieByMovieId(int id) {
        String sql = "SELECT movie_id, the_one_movie_id, name, " +
                    "runtime_in_minutes, budget_in_millions " +
                    "FROM movie " +
                    "WHERE movie_id = ?;";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, id);
        if (result.next()){
            return mapRowToMovie(result);
        }
        return null;
    }

    @Override
    public Movie addNewMovie(Movie movie) {
        String sql = "INSERT INTO movie (the_one_movie_id, name, runtime_in_minutes, budget_in_millions) " +
                    "VALUES (?, ?, ?, ?) RETURNING movie_id;";
        Integer movieId = jdbcTemplate.queryForObject(sql, Integer.class, movie.getTheOneMovieId(), movie.getName(), movie.getBudgetInMillions(), movie.getRuntimeInMinutes());

        //verify that the movie was written to the db
        return getMovieByMovieId(movieId);
    }

    @Override
    public void deleteMovie(int id) {
        String sql = "DELETE FROM movie WHERE movie_id = ?;";
        jdbcTemplate.update(sql, id);
    }

    private Movie mapRowToMovie(SqlRowSet rs) {
        Movie movie = new Movie();
        movie.setMovieId(rs.getInt("movie_id"));
        movie.setTheOneMovieId(rs.getString("the_one_movie_id"));
        movie.setName(rs.getString("name"));
        movie.setRuntimeInMinutes(rs.getInt("runtime_in_minutes"));
        movie.setBudgetInMillions(rs.getInt("budget_in_millions"));
        return movie;
    }
}
