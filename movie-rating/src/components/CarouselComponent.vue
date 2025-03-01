<template>
  <n-carousel
    style="padding: 24px"
    autoplay
    :slides-per-view="3"
    :space-between="20"
    :loop="false"
    draggable
  >
    <img
      class="carousel-img"
      v-for="movie in movies.slice(0, 5)"
      :key="movie.id"
      :src="`https://image.tmdb.org/t/p/w500/${movie.poster_path}`"
      :alt="movie.title"
    />
  </n-carousel>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import axios from 'axios'

// API Key from .env
const API_KEY = import.meta.env.VITE_TMDB_API_KEY
const BASE_URL = 'https://api.themoviedb.org/3'

const movies = ref([])

// Fetch now-playing movies
const fetchMovies = async () => {
  try {
    const response = await axios.get(`${BASE_URL}/movie/now_playing`, {
      params: { api_key: API_KEY },
    })
    movies.value = response.data.results
  } catch (error) {
    console.error('Error fetching movies:', error)
  }
}

// Fetch movies when the component is mounted
onMounted(fetchMovies)
</script>

<style scoped>
.carousel-img {
  width: 100%;
  height: 650px;
  object-fit: cover;
}
</style>
