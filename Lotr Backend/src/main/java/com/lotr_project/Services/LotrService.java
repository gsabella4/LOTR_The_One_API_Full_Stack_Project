package com.lotr_project.Services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lotr_project.Models.*;
import com.lotr_project.Models.Character;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class LotrService {

    //attributes
    @Value("${LOTR_API}")
    private String LOTR_API;

    @Value("${KEY}") //need to get API key and add to application.properties
    private String KEY;

    RestTemplate restTemplate = new RestTemplate();


    // long way
    // method to walk through Json using object mapper
    public List<Movie> getAllMovies(){
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(KEY);

        HttpEntity<Void> entity = new HttpEntity<>(headers); //wraps our header into an entity to pass in our get request

        ObjectMapper mapper = new ObjectMapper(); // is for walking through the nodes of the json
        JsonNode jsonNode; // walking through the nodes
        ResponseEntity<String> responseEntity =
                restTemplate.exchange(LOTR_API + "/movie", HttpMethod.GET, entity, String.class);

        List<Movie> movieList = new ArrayList<>(); // empty data structure that we will fill

        try {
            jsonNode = mapper.readTree(responseEntity.getBody());
            JsonNode docs = jsonNode.path("docs");

            for (int i = 0; i < docs.size(); i++) { // looping through the array named docs

                Movie temp = new Movie(); //empty movie object

                //get theOneMovieId and set to temp
                String movieId = docs.path(i).path("_id").asText();
                temp.setTheOneMovieId(movieId);

                //get name and set to temp
                String name = docs.path(i).path("name").asText();
                temp.setName(name);

                //get runtimeInMinutes and set to temp
                int runtimeInMinutes = docs.path(i).path("runtimeInMinutes").asInt();
                temp.setRuntimeInMinutes(runtimeInMinutes);

                //get budgetInMillions and set to temp
                int budgetInMillions = docs.path(i).path("budgetInMillions").asInt();
                temp.setBudgetInMillions(budgetInMillions);

                movieList.add(temp);
            }

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return movieList;
    }

    // easier method, utilizing the MovieDocs model
    public List<Movie> loadAllMovies(){
        ResponseEntity<MovieDocs> responseEntity =
                restTemplate.exchange(LOTR_API + "/movie",
                        HttpMethod.GET, makeEntity(), MovieDocs.class);

        MovieDocs movieDocs = responseEntity.getBody();

        Movie[] movies = movieDocs.getMovies();

        return Arrays.asList(movies);
    }

    // method utilizing CharacterDocs model, loads all characters
    public List<Character> loadAllCharacters(){
        ResponseEntity<CharacterDocs> responseEntity =
                restTemplate.exchange(LOTR_API + "/character",
                        HttpMethod.GET, makeEntity(), CharacterDocs.class);

        Character[] characters = responseEntity.getBody().getCharacters();
        return Arrays.asList(characters);
    }

    // method utilizing QuoteDocs model, loads all quotes
    public List<Quote> loadAllQuotes(){
        ResponseEntity<QuoteDocs> responseEntity =
                restTemplate.exchange(LOTR_API + "/quote",
                        HttpMethod.GET, makeEntity(), QuoteDocs.class);

        Quote[] quotes = responseEntity.getBody().getQuotes();
        return Arrays.asList(quotes);
    }

    // method utilizing QuoteDocs model, loads all quotes for Movie Id
    public List<Quote> getAllQuotesByMovieId(String id){
        ResponseEntity<QuoteDocs> responseEntity =
                restTemplate.exchange(LOTR_API + "/movie/" + id + "/quote",
                        HttpMethod.GET, makeEntity(), QuoteDocs.class);
        Quote[] quotes = responseEntity.getBody().getQuotes();
        return Arrays.asList(quotes);
    }

    // method utilizing QuoteDocs model, loads all quotes for Character Id
    public List<Quote> getAllQuotesByCharacterId(String id){
        ResponseEntity<QuoteDocs> responseEntity =
                restTemplate.exchange(LOTR_API + "/character/" + id + "/quote",
                        HttpMethod.GET, makeEntity(), QuoteDocs.class);
        Quote[] quotes = responseEntity.getBody().getQuotes();
        return Arrays.asList(quotes);
    }

    // method utilizing CharacterDocs model, get Character by Character id
    public Character getCharacterById(String id){
        ResponseEntity<CharacterDocs> responseEntity =
                restTemplate.exchange(LOTR_API + "/character/" + id,
                        HttpMethod.GET, makeEntity(), CharacterDocs.class);
        Character[] character = responseEntity.getBody().getCharacters();
        return character[0];
    }

    // helper method to create entity
    private HttpEntity<Void> makeEntity(){
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(KEY);

        HttpEntity<Void> entity = new HttpEntity<>(headers);
        return entity;
    }
}
