package com.lotr_project.Models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class QuoteDocs {

    @JsonProperty("docs")
    private Quote[] quotes;

    public Quote[] getQuotes() {
        return quotes;
    }

    public void setQuotes(Quote[] quotes) {
        this.quotes = quotes;
    }
}
