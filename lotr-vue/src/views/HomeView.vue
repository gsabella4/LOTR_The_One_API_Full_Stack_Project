<template>
  <div class="home">
    <home-page />
  </div>
</template>

<script>
// @ is an alias to /src
import HomePage from '@/components/HomePage.vue'

export default {
  name: 'HomeView',
  components: {
    HomePage
  }
}
</script>
<style>
.home {
  display: flex;
  justify-content: center;
}
</style>
