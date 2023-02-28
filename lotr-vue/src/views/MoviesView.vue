<template>
  <div>
    <h1>Movie Collection</h1>
    <div class="movie-container">
      <movie-content v-for="movie in listOfMovies" v-bind:key="movie.id" 
      :movie="movie" /><!-- shortcut for v-bind:movie="movie" -->
    </div>
  </div>
</template>

<script>
import MovieContent from '@/components/MovieContent.vue';
import BackendService from '../services/BackendService'

export default {
  components: { MovieContent },
  name: "movies-view",
  data() {
    return {
      listOfMovies: []
    }
  },
  created() {
    BackendService.viewMoviesFromAPI()
    .then((response) => {
      this.listOfMovies = response.data;
    })
  }
};
</script>

<style>

.movie-container {
  display: flex;
  flex-wrap: wrap;
  justify-content: space-evenly;
}



</style>