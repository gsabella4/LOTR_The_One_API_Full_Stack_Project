<template>
  <div class="favorite-characters">
    <h2 class="character-name">{{ character.name }}</h2>
    <p>{{ character.race }}</p>
    <a :href="character.wikiUrl" target="blank">Wiki Page</a><br><br>
    <button class='remove-btn' v-on:click.prevent="removeFromFavorites(character.characterId)">Remove</button>
  </div>
</template>

<script>
import backendService from '@/services/BackendService'

export default {
    name: 'favorite-characters',
    props: {
        character: Object
    },
    methods: {
        removeFromFavorites(characterId) {
            if (confirm("Remove character from your favorites?")){
                backendService.deleteCharacterFromDB(characterId).then((response) => {
                    if (response.status == 204){
                        console.log(response);
                        alert("Character removed!");
                        window.location.reload();
                    }
                }).catch((response) => {
                    console.log(response);
                    alert("Error removing character from favorites");
                });
            }
        }
    }
}
</script>

<style>
.favorite-characters {
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