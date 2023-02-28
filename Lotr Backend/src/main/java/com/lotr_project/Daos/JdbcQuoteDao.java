package com.lotr_project.Daos;

import com.lotr_project.Models.Quote;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcQuoteDao implements QuoteDao {

    private JdbcTemplate jdbcTemplate;

    public JdbcQuoteDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Quote> getAllQuotes() {
        String sql = "SELECT quote_id, the_one_quote_id, dialog " +
                    "the_one_movie_id, the_one_character_id " +
                    "FROM quote;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        List<Quote> quoteList = new ArrayList<>();
        while(results.next()){
            Quote quote = mapRowToQuote(results);
            quoteList.add(quote);
        }
        return quoteList;
    }

    @Override
    public Quote getQuoteByQuoteId(int id) {
        String sql = "SELECT quote_id, the_one_quote_id, dialog " +
                    "the_one_movie_id, the_one_character_id " +
                    "FROM quote " +
                    "WHERE quote_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);
        if(results.next()){
            Quote quote = mapRowToQuote(results);
            return quote;
        }
        return null;
    }

    @Override
    public Quote addNewQuote(Quote quote) {
        String sql = "INSERT INTO quote (the_one_quote_id, dialog, the_one_movie_id, the_one_character_id) " +
                    "VALUES (?, ?, ?, ?) RETURNING quote_id;";
        Integer quoteId = jdbcTemplate.queryForObject(sql, Integer.class, quote.getTheOneQuoteId(), quote.getDialog(),
                        quote.getTheOneMovieId(), quote.getTheOneCharacterId());

        //verify that the quote was written to the db
        return getQuoteByQuoteId(quoteId);
    }

    @Override
    public void deleteQuote(int id) {
        String sql = "DELETE FROM quote WHERE quote_id = ?;";
        jdbcTemplate.update(sql, id);
    }

    private Quote mapRowToQuote(SqlRowSet rs) {
        Quote quote = new Quote();
        quote.setQuoteId(rs.getInt("quote_id"));
        quote.setTheOneQuoteId(rs.getString("the_one_quote_id"));
        quote.setDialog(rs.getString("dialog"));
        quote.setTheOneMovieId(rs.getString("the_one_movie_id"));
        quote.setTheOneCharacterId(rs.getString("the_one_character_id"));
        return quote;
    }
}
