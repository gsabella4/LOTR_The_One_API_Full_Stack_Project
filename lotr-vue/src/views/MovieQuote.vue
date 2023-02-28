<template>
  <div id="quotes">
    <h1>Movie Quotes!</h1>
    <p v-for="quote in theQuotes" :key="quote._id">
      {{ quote.dialog }}
      </p>
  </div>
</template>

<script>
import BackendService from '@/services/BackendService'
export default {
  name: "movie-quote",
  data() {
    return {
      theQuotes: []
    }
  },
  created() {
    BackendService.viewQuotesByMovieIdFromAPI(this.$route.params.id).then((response) => {
      this.theQuotes = response.data;
    })
  }

}
</script>

<style>

</style>