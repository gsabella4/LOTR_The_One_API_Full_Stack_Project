import Vue from 'vue'
import VueRouter from 'vue-router'
import HomeView from '../views/HomeView.vue'
import Movies from '../views/MoviesView.vue'
import MovieQuote from '@/views/MovieQuote.vue'
import Characters from '@/views/CharactersView.vue'
import Favorites from '@/views/FavoritesView.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'HomeView',
    component: HomeView
  },
  {
    path: '/movies',
    name: 'Movies',
    component: Movies
  },
  {
    path: '/characters',
    name: 'Characters',
    component: Characters
  },
  {
    path: '/movie-quote',
    name: 'movie-quote',
    component: MovieQuote
  },
  {
    path: '/favorites',
    name: 'Favorites',
    component: Favorites
  }
]

const router = new VueRouter({
  routes
})

export default router
