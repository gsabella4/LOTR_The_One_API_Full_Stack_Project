<template>
  <div class="favorite-movies">
    <router-link :to="{ name: 'movie-quote', params: { id: movie._id }}">
    <h2 class="movie-name">{{ movie.name }}</h2>
    </router-link>
    <p>Runtime in Minutes: {{ movie.runtimeInMinutes }}</p>
    <p>Budget: ${{ movie.budgetInMillions }} million</p>
    <button class='remove-btn' v-on:click.prevent="removeFromFavorites(movie.movieId)">Remove</button>
  </div>
</template>

<script>
import backendService from '@/services/BackendService'

export default {
    name: 'favorite-movies',
    props: {
        movie: Object
    },
    methods: {
        removeFromFavorites(movieId) {
            if (confirm("Remove movie from your favorites?")){
                backendService.deleteMovieFromDB(movieId).then((response) => {
                    if (response.status == 204){
                        console.log(response);
                        alert("Movie removed!");
                        window.location.reload();
                    }
                }).catch((response) => {
                    console.log(response);
                    alert("Error removing movie from favorites");
                });
            }
        }
    }

}
</script>

<style>
.favorite-movies {
    border: 10px ridge #e6a007;
    background-color: antiquewhite;
    border-radius: 10px;
    box-shadow: 5px 5px 10px black;
    width: 200px;
    height: 250px;
    margin: 20px;
    padding: 15px;
}

.movie-name {
    color: darkred
}

.remove-btn {
  position: relative;
  display: inline-block;
  margin: 15px;
  padding: 10px 20px;
  text-align: center;
  font-size: 15px;
  letter-spacing: 1px;
  text-decoration: none;
  color: crimson;
  background: transparent;
  cursor: pointer;
  transition: ease-out 0.5s;
  border: 2px solid crimson;
  border-radius: 10px;
  box-shadow: inset 0 0 0 0 crimson;
}

.remove-btn:hover {
  color: white;
  box-shadow: inset 0 -100px 0 0 crimson;
}

.remove-btn:active {
  transform: scale(0.9);
}

</style>