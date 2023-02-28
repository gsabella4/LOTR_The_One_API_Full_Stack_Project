package com.lotr_project.Controllers;

import com.lotr_project.Models.Character;
import com.lotr_project.Models.Movie;
import com.lotr_project.Models.Quote;
import com.lotr_project.Services.LotrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
public class LotrController {

    @Autowired // Autowired allows SpringBoot to inject the service
    LotrService service;

    @GetMapping(path = "/movie")
    public List<Movie> getAllMoviesFromApi() {
        return service.loadAllMovies();
    }

    @GetMapping("/character")
    public List<Character> getAllCharactersFromApi(){
        return service.loadAllCharacters();
    }

    @GetMapping("/character/{id}")
    public Character getCharacterById(@PathVariable String id) { return service.getCharacterById(id); }

    @GetMapping("/quote")
    public List<Quote> loadAllQuotesFromApi() {
        return service.loadAllQuotes();
    }

    @GetMapping("/quotesByMovie/{id}")
    public List<Quote> loadAllQuotesByMovieId(@PathVariable String id) {
        return service.getAllQuotesByMovieId(id);
    }

    @GetMapping("quotesByCharacter/{id}")
    public List<Quote> loadAllQuotesByCharacterId(@PathVariable String id) {
        return service.getAllQuotesByCharacterId(id);
    }
}
