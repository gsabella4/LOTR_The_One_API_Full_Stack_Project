<template>
  <div class="card">
    <router-link :to="{ name: 'character-quote', params: { id: character._id }}">
    <h2 class="character-name">{{ character.name }}</h2>
    </router-link>
    <p>{{ character.race }}</p>
    <a :href="character.wikiUrl" target="blank">Wiki Page</a><br><br>
    <button v-on:click.prevent="addToFavorites(character)">+ Add</button>
  </div>
</template>

<script>
import backendService from '../services/BackendService'

export default {
    name: 'character-content',
    props: {
        character: Object
    },
    methods: {
        addToFavorites(character) {
            //if (confirm("Add character to your favorites?")){
                backendService.addCharacterToDB(character).then((response) => {
                    if (response.status == 201){
                        console.log(response);
                        alert("Character added!");
                    }
                }).catch((response) => {
                    console.log(response);
                    alert("Error adding character to favorites");
                });
            //}
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

.character-name {
    color: darkred
}
</style>