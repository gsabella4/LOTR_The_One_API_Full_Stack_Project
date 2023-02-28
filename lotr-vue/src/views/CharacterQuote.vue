<template>
  <div id="quotes">
      <h1>{{ character.name }}'s Quotes</h1>
      <p v-for="quote in theQuotes" :key="quote._id">{{ quote.dialog }}</p>
  </div>
</template>

<script>
import BackendService from '@/services/BackendService'

export default {
  name: "character-quote",
  data() {
    return {
      theQuotes: [],
      character: {}
    };
  },
  created() {
    BackendService.viewCharacterByIdFromAPI(this.$route.params.id).then((response) => {
      this.character = response.data;

    }),
    BackendService.viewQuotesByCharacterIdFromAPI(this.$route.params.id).then((response) => {
      this.theQuotes = response.data;
    });
  },
};
</script>

<style>
#quotes {
    border: 10px ridge #e6a007;
    background-color: antiquewhite;
    border-radius: 10px;
    box-shadow: 5px 5px 10px black;
    margin: 50px;
}
</style>