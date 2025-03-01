<template>
  <div>
    <n-scrollbar x-scrollable>
      <div class="scroll-container">
        <div class="card-container">
          <router-link v-for="card in movies" :key="card.id" :to="`/movie/${card.id}`">
            <CardComponent
              :id="card.id"
              :title="card.title"
              :image="`https://image.tmdb.org/t/p/w500/${card.poster_path}`"
              :content="card.overview"
            />
          </router-link>
        </div>
      </div>
    </n-scrollbar>
  </div>
</template>

<script setup>
import { ref, watchEffect } from 'vue'
import axios from 'axios'
import CardComponent from '@/components/CardComponent.vue'

// API Key from .env
const API_KEY = import.meta.env.VITE_TMDB_API_KEY
const BASE_URL = 'https://api.themoviedb.org/3'

// Declare 'selectedCategory' as a prop
const props = defineProps({
  selectedCategory: {
    type: String,
    required: true,
    default: 'popular', // Default value if not provided
  },
})

const movies = ref([])

// Define categories with year filters
const categories = [
  {
    id: 'popular',
    name: 'Popular',
    endpoint: '/movie/popular',
  },
  {
    id: 'romance',
    name: 'Romance', // Replacing K-Drama with Romance
    endpoint:
      '/discover/movie?with_genres=10749&primary_release_date.gte=2020&primary_release_date.lte=2025', // Romance genre code 10749
  },
  {
    id: 'comedy',
    name: 'Comedy', // Replacing C-Drama with Comedy
    endpoint:
      '/discover/movie?with_genres=35&primary_release_date.gte=2020&primary_release_date.lte=2025', // Comedy genre code 35
  },
  {
    id: 'hollywood',
    name: 'Hollywood',
    endpoint: '/discover/movie?with_origin_country=US',
  },
  {
    id: 'anime',
    name: 'Anime',
    endpoint:
      '/discover/movie?with_genres=16&primary_release_date.gte=2020&primary_release_date.lte=2025',
  },
]

// Fetch movies dynamically
const fetchMovies = async () => {
  const category = categories.find((c) => c.id === props.selectedCategory)
  if (!category) return

  try {
    const response = await axios.get(`${BASE_URL}${category.endpoint}`, {
      params: { api_key: API_KEY },
    })
    movies.value = response.data.results
  } catch (error) {
    console.error('Error fetching movies:', error)
  }
}

// Fetch movies when category changes
watchEffect(fetchMovies)
</script>

<style scoped>
.scroll-container {
  overflow-x: auto;
  white-space: nowrap;
  padding: 24px;
  display: flex;
}

.card-container {
  display: flex;
  gap: 16px;
}

.card-container > a {
  flex: 0 0 auto;
  width: 200px;
}
</style>
