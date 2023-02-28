<template>
  <div class="card">
    <router-link :to="{ name: 'movie-quote', params: { id: movie._id }}">
    <h2 class="movie-name">{{ movie.name }}</h2>
    </router-link>
    <p>Runtime in Minutes: {{ movie.runtimeInMinutes }}</p>
    <p>Budget: ${{ movie.budgetInMillions }} million</p>
    <button v-on:click.prevent="addToFavorites(movie)">+ Add</button>
  </div>
</template>

<script>
import backendService from '../services/BackendService'

export default {
    name: 'movie-content',
    props: {
        movie: Object
    },
    methods: {
        addToFavorites(movie) {
            if (confirm("Add movie to your favorites? ")){
            backendService.addMovieToDB(movie).then((response) => {
                if (response.status == 201){
                    console.log(response);
                    alert("Movie added!");
                }
                }).catch((response) => {
                    console.log(response);
                    alert("Error adding movie to favorites");
                });
            }
        }
    }
}
</script>

<style>
.card {
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

button {
  position: relative;
  display: inline-block;
  margin: 15px;
  padding: 10px 20px;
  text-align: center;
  font-size: 15px;
  letter-spacing: 1px;
  text-decoration: none;
  color: #43ac38;
  background: transparent;
  cursor: pointer;
  transition: ease-out 0.5s;
  border: 2px solid #43ac38;
  border-radius: 10px;
  box-shadow: inset 0 0 0 0 #43ac38;
}

button:hover {
  color: white;
  box-shadow: inset 0 -100px 0 0 #43ac38;
}

button:active {
  transform: scale(0.9);
}

</style>