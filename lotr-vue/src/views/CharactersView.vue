<template>
  <div>
    <h1>Characters</h1>
    <span>Filter By Name:</span><input type="text" id="nameFilter" v-model="filter.name"/>&nbsp;
    <span>Filter By Identification:</span><input type="text" id="raceFilter" v-model="filter.race"/>
    <div class="character-container">
        <character-content v-for="character in filteredCharacters" v-bind:key="character.id"
        :character="character"/>
    </div>
  </div>
</template>

<script>
import CharacterContent from '@/components/CharacterContent.vue'
import BackendService from '@/services/BackendService'

export default {
  components: { CharacterContent },
  name: "characters-view",
  created() {
    BackendService.viewCharactersFromAPI()
    .then((response) => {
        this.listOfCharacters = response.data;
    });
  },
  data() {
    return {
        listOfCharacters: [],
        filter : {
          name: "",
          race: ""
        }
    }
  },
  computed: {
    filteredCharacters() {
      let filteredCharacters = this.listOfCharacters;
      if(this.filter.name != "") {
        filteredCharacters = filteredCharacters.filter((character) => 
        character.name
          .toLowerCase()
          .includes(this.filter.name.toLowerCase())
        );
      }
      if(this.filter.race != "") {
        filteredCharacters = filteredCharacters.filter((character) => 
        character.race
        .toLowerCase()
        .includes(this.filter.race.toLowerCase())
        );
      }
      return filteredCharacters;
    }
  }
  
};
</script>

<style>

.character-container {
    display: flex;
    flex-wrap: wrap;
    justify-content: space-evenly;
}

</style>