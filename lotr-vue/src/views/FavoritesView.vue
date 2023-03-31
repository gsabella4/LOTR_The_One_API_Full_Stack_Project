<template>
  <div class="favorites">
    <h1 class="favorite-headers">Favorite Movies</h1>
    <div class="movie-container">
        <favorite-movies v-for="movie in favoriteMovies" v-bind:key="movie.id"
        :movie="movie"/>
    </div>
    <h3 class="empty-movies" v-if="favoriteMovies.length == 0">You haven't added any movies to your favorites!</h3>
    <h1 class="favorite-headers">Favorite Characters</h1>
    <div class="character-container">
        <favorite-characters v-for="character in favoriteCharacters" v-bind:key="character.id"
        :character="character"/>
    </div>
    <h3 class="empty-characters" v-if="favoriteCharacters.length == 0">You haven't added any characters to your favorites!</h3>
  </div>
</template>

<script>
import FavoriteMovies from '@/components/FavoriteMovies.vue'
import FavoriteCharacters from '@/components/FavoriteCharacters.vue'
import BackendService from '@/services/BackendService';
export default {
  components: { FavoriteMovies, FavoriteCharacters },
    name: 'FavoritesView',
    data() {
        return {
            favoriteMovies: [],
            favoriteCharacters: []
        }
    },
    created() {
        BackendService.viewMoviesFromDB()
        .then((response) => {
            this.favoriteMovies = response.data;
        });
        BackendService.viewCharactersFromDB()
        .then((response) => {
            this.favoriteCharacters = response.data;
        });
    }
};
</script>

<style>

.favorites {
    display: flex;
    flex-direction: column;
    align-items: center;
}

.movie-container {
    display: flex;
    flex-wrap: wrap;
    justify-content: space-evenly;
}

.favorite-headers {
    border: 5px ridge #e6a007;
    background-color: antiquewhite;
    width: 400px;
    padding: 7px;
    border-radius: 5px;
    box-shadow: 2px 2px 5px black;
}

h3 {
    border: 5px ridge #e6a007;
    background-color: antiquewhite;
    width: 500px;
    padding: 5px;
    border-radius: 3px;
    box-shadow: 1px 1px 3px black;
}


</style>