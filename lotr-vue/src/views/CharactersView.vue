<template>
  <div>
    <h1>Characters</h1>
    <div class="character-container">
        <character-content v-for="character in listOfCharacters" v-bind:key="character.id"
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
  data() {
    return {
        listOfCharacters: []
    }
  },
  created() {
    BackendService.viewCharactersFromAPI()
    .then((response) => {
        this.listOfCharacters = response.data;
    })
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