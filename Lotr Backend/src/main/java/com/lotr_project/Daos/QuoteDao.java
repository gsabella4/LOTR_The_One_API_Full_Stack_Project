package com.lotr_project.Daos;

import com.lotr_project.Models.Quote;

import java.util.List;

public interface QuoteDao {

    //Read all quotes
    List<Quote> getAllQuotes();

    //Read quote by quote_id
    Quote getQuoteByQuoteId(int id);

    //Create quote
    Quote addNewQuote(Quote quote);

    //Delete quote
    void deleteQuote(int id);
}
