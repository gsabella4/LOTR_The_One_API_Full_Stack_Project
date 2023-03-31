<template>
  <div id="app">
    <nav>
      <router-link to="/">Home</router-link> |
      <router-link v-bind:to="{name: 'Movies'}">Movies</router-link> |
      <router-link v-bind:to="{name: 'Characters'}">Characters</router-link> |
      <router-link v-bind:to="{name: 'Favorites'}">Favorites</router-link>
    </nav>
    <router-view/>
  </div>
</template>

<style>

html {
  background-image: url('@/assets/gandalfBackground.jpeg');
  background-size: 100%;
  background-repeat: no-repeat;
  background-attachment: fixed;
  background-position: center;
  min-height: 100%;
  width: 100vw;
}

#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

nav {
  padding: 30px;
}

nav a {
  font-weight: bold;
  color: #2c3e50;
}

nav a.router-link-exact-active {
  color: darkred;
}
</style>
