package com.lotr_project.Models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MovieTest {

    private Movie movie = new Movie();

    @BeforeEach
    public void setUp() {
        this.movie.setName("The Revenge of Java");
        this.movie.setRuntimeInMinutes(220);
        this.movie.setBudgetInMillions(12);
    }


    @Test
    public void object_instantiates_correctly() {

        assertEquals("The Revenge of Java", this.movie.getName());
        assertEquals(220, this.movie.getRuntimeInMinutes());
        assertEquals(12, this.movie.getBudgetInMillions());
    }

    @Test
    public void cost_per_minute_calculated_correctly() {

        double expectedResult = 12.0/220.0;
        double actualResult = this.movie.getCostPerMinute();
        assertEquals(expectedResult, actualResult);
    }
}
