package com.lotr_project.Services;

import com.lotr_project.Models.Movie;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.contains;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class LotrServiceTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private LotrService service = new LotrService();

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    public String readTestFile() {

        String filePath = "src/test/resources/testMoviesResponse.json";
        String content = null;
        try {
            content = new String(Files.readAllBytes(Paths.get(filePath)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }

    public boolean movieCompare(Movie expected, Movie actual) {

        if (!expected.getName().equals(actual.getName())) {
            return false;
        }

        if (expected.getRuntimeInMinutes() != actual.getRuntimeInMinutes()) {
            return false;
        }

        if (expected.getBudgetInMillions() != actual.getBudgetInMillions()) {
            return false;
        }

        return true;
    }

    @Test
    public void test_get_all_movies() {

        ResponseEntity<String> mockedResponse = new ResponseEntity<>(readTestFile(),
                HttpStatus.OK);

        when(
                restTemplate.exchange(
                        contains("/movie"),
                        eq(HttpMethod.GET),
                        any(HttpEntity.class),
                        eq(String.class)
                )

        ).thenReturn(mockedResponse);

        List<Movie> movies = service.getAllMovies();
        assertEquals(2, movies.size());

        Movie expectedMovie = new Movie();
        expectedMovie.setName("How Big Business Stole from Gollum");
        expectedMovie.setBudgetInMillions(2);
        expectedMovie.setRuntimeInMinutes(10);

        boolean objectsTheSame = movieCompare(expectedMovie, movies.get(0));
        assertTrue(objectsTheSame, "Objects don't match");

    }

}
