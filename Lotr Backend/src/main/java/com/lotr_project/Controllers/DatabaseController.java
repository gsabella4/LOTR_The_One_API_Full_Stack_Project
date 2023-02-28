package com.lotr_project.Controllers;

import com.lotr_project.Daos.CharacterDao;
import com.lotr_project.Daos.MovieDao;
import com.lotr_project.Daos.QuoteDao;
import com.lotr_project.Models.Character;
import com.lotr_project.Models.Movie;
import com.lotr_project.Models.Quote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@CrossOrigin
public class DatabaseController {

    //MovieDao Methods
    @Autowired
    MovieDao movieDao;

    @GetMapping("/myMovies")
    public List<Movie> getAllMovies() {
        return movieDao.getAllMovies();
    }

    @GetMapping("/myMovies/{id}")
    public Movie getMovieByMovieId(@PathVariable int id) {
        return movieDao.getMovieByMovieId(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/add-movie")
    public Movie addMoveToDatabase(@RequestBody Movie movie) {
        if (movieDao.getMovieByMovieId(movie.getMovieId()) == null) {
            return movieDao.addNewMovie(movie);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/myMovies/{id}")
    public void deleteMovie(@PathVariable int id) {
        if (movieDao.getMovieByMovieId(id) != null){
            movieDao.deleteMovie(id);
        }
    }


    //CharacterDao Methods
    @Autowired
    CharacterDao characterDao;

    @GetMapping("/myCharacters")
    public List<Character> getAllCharacters() { return characterDao.getAllCharacters(); }

    @GetMapping("/myCharacters/{id}")
    public Character getCharacterByCharacterId(@PathVariable int id) { return characterDao.getCharacterByCharacterId(id); }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/add-character")
    public Character addCharacterToDatabase(@RequestBody Character character) { return characterDao.addNewCharacter(character); }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("myCharacters/{id}")
    public void deleteCharacter(@PathVariable int id) {
        if (characterDao.getCharacterByCharacterId(id) != null) {
            characterDao.deleteCharacter(id);
        }
    }


    //QuoteDao Methods
    @Autowired
    QuoteDao quoteDao;

    @GetMapping("/myQuotes")
    public List<Quote> getAllQuotes() { return quoteDao.getAllQuotes(); }

    @GetMapping("/myQuotes/{id}")
    public Quote getQuoteByQuoteId(@PathVariable int id) { return quoteDao.getQuoteByQuoteId(id); }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/add-quote")
    public Quote addQuoteToDatabase(@RequestBody Quote quote) { return quoteDao.addNewQuote(quote); }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("myQuotes/{id}")
    public void deleteQuote(@PathVariable int id) {
        if (quoteDao.getQuoteByQuoteId(id) != null){
            quoteDao.deleteQuote(id);
        }
    }
}
