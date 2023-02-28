import axios from 'axios';

const http = axios.create({
    baseURL: 'http://localhost:9000'
});

export default {
    //Movie Services
    //API
    viewMoviesFromAPI() {
        return http.get("/movie");
    },

    //Database
    addMovieToDB(movie) {
        return http.post("/add-movie", movie);
    },
    viewMoviesFromDB() {
        return http.get("/myMovies");
    },
    viewMovieByMovieIdFromDB(movieId) {
        return http.get(`/myMovies/${movieId}`);
    },
    deleteMovieFromDB(movieId) {
        return http.delete(`/myMovies/${movieId}`);
    },


    //Character Services
    //API
    viewCharactersFromAPI() {
        return http.get("/character")
    },
    viewCharacterByIdFromAPI(characterId) {
        return http.get(`/character/${characterId}`);
    },

    //Database
    addCharacterToDB(character) {
        return http.post("/add-character", character);
    },
    viewCharactersFromDB() {
        return http.get("/myCharacters");
    },
    viewCharacterByCharacterIdFromDB(characterId) {
        return http.get(`/myCharacters/${characterId}`);
    },
    deleteCharacterFromDB(characterId) {
        return http.delete(`/myCharacters/${characterId}`);
    },


    //Quote Services
    //API
    viewQuotesFromAPI() {
        return http.get("/quote");
    },
    viewQuotesByMovieIdFromAPI(movieId) {
        return http.get(`/quotesByMovie/${movieId}`);
    },
    viewQuotesByCharacterIdFromAPI(characterId) {
        return http.get(`/quotesByCharacter/${characterId}`);
    },

    //Database
    addQuoteToDB(quote) {
        return http.post("/add-quote", quote);
    },
    viewQuotesFromDB() {
        return http.get("/myQuotes");
    },
    viewQuoteByQuoteIdFromDB(quoteId) {
        return http.get(`/myQuotes/${quoteId}`);
    },
    deleteQuoteFromDB(quoteId) {
        return http.delete(`/myQuotes/${quoteId}`);
    }
}
