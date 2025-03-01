<template>
  <div class="movie-detail" v-if="movie">
    <div class="movie-banner" style="background-color: blanchedalmond">
      <img :src="movie.image" alt="Movie Background" class="banner-image" />
      <div class="overlay"></div>
      <div class="movie-info">
        <p style="font-size: 4rem; padding-bottom: 24px">{{ movie.title }}</p>
        <div class="movie-meta">
          <span class="rating">{{ movie.rating }} ⭐ </span>
          <span class="release-date">{{ movie.releaseDate }}</span>
          <span class="genre">{{ movie.genre }}</span>
        </div>
        <p class="description" style="font-size: 1.2rem">{{ movie.content }}</p>

        <!-- Buttons for viewing and rating -->
        <div class="buttons">
          <!-- Watch Trailer Button -->
          <button v-if="movie.trailer" @click="openTrailerModal" class="trailer-button">
            Watch Trailer
          </button>

          <!-- <button @click="watchMovie" class="watch-button">Review</button> -->
          <button @click="rateMovie" class="rate-button">Rating</button>

          <!-- save or add to favorite -->
          <FavoriteButton />
        </div>
      </div>

      <ReviewComponent />
    </div>

    <!-- Modal for Rating -->
    <div v-if="isRatingModalOpen" class="modal-overlay" @click="closeRatingModal">
      <div class="modal-content" @click.stop>
        <RatingComponent v-model="rating" :initialRating="rating" :maxRating="5" />
        <font-awesome-icon @click="closeRatingModal" class="close-button" icon="xmark" />
      </div>
    </div>

    <!-- Modal for Trailer -->
    <div v-if="isModalOpen" class="modal-overlay" @click="closeTrailerModal">
      <div class="modal-content-trailer" @click.stop>
        <iframe
          width="100%"
          height="400px"
          :src="`https://www.youtube.com/embed/${movie.trailer}`"
          frameborder="0"
          allow="accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture"
          allowfullscreen
        ></iframe>
        <font-awesome-icon @click="closeTrailerModal" class="close-button" icon="xmark" />
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { useRoute } from 'vue-router'
import axios from 'axios'
import RatingComponent from '@/components/RatingComponent.vue'
import ReviewComponent from '../ReviewComponent.vue'
import FavoriteButton from '@/components/FavoriteButton.vue'

const route = useRoute()
const movie = ref(null)
const isRatingModalOpen = ref(false) // Track rating modal visibility
const isModalOpen = ref(false) // Track modal visibility

// API Key and base URL for TMDB
const API_KEY = import.meta.env.VITE_TMDB_API_KEY
const BASE_URL = 'https://api.themoviedb.org/3'

// Fetch the movie details using the movie ID from the route
onMounted(async () => {
  const movieId = route.params.id // Get the ID from the URL

  try {
    // Fetch movie details from the API
    const response = await axios.get(`${BASE_URL}/movie/${movieId}`, {
      params: { api_key: API_KEY },
    })

    const movieData = response.data

    // Fetch movie videos (trailers, highlights)
    const videoResponse = await axios.get(`${BASE_URL}/movie/${movieId}/videos`, {
      params: { api_key: API_KEY },
    })
    const trailer = videoResponse.data.results.find(
      (video) => video.type === 'Trailer' && video.site === 'YouTube',
    )

    // Set movie details to the movie object
    movie.value = {
      title: movieData.title,
      image: `https://image.tmdb.org/t/p/original${movieData.backdrop_path}`,
      content: movieData.overview,
      rating: movieData.vote_average,
      releaseDate: movieData.release_date,
      genre: movieData.genres.map((genre) => genre.name).join(', '), // Map genres to string
      trailer: trailer ? trailer.key : null, // Store the trailer key if found
    }
  } catch (error) {
    console.error('Error fetching movie details:', error)
  }
})

// Open the rating modal
const rateMovie = () => {
  isRatingModalOpen.value = true
}

// Close the rating modal
const closeRatingModal = () => {
  isRatingModalOpen.value = false
}

// Open the trailer modal
const openTrailerModal = () => {
  isModalOpen.value = true
}

// Close the trailer modal
const closeTrailerModal = () => {
  isModalOpen.value = false
}
</script>

<style scoped>
.movie-banner {
  position: relative;
  width: 100%;
  height: 700px;
  background-color: black;
}

.banner-image {
  width: 100%;
  height: 100%;
  object-fit: cover;
  filter: brightness(0.5);
}

.overlay {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background: linear-gradient(to right, rgba(0, 0, 0, 0.7), rgba(255, 255, 255, 0.2));
}

.movie-info {
  width: 50%;
  position: absolute;
  bottom: 20%;
  left: 10%;
  color: white;
  z-index: 1;
}

.movie-meta span {
  padding-top: 24px;
  padding-right: 24px;
  font-size: 2rem;
}

.buttons {
  padding-top: 24px;
  display: flex;
  gap: 15px;
}

.watch-button {
  background-color: #e50914;
  color: white;
  font-size: 16px;
  padding: 10px 20px;
  border: none;
  cursor: pointer;
  border-radius: 5px;
}

.watch-button:hover {
  background-color: #f40612;
}

.rate-button {
  background-color: #f5c518;
  color: black;
  font-size: 16px;
  padding: 10px 20px;
  border: none;
  cursor: pointer;
  border-radius: 5px;
}

.rate-button:hover {
  background-color: #e4b400;
}

/* Styles for Trailer Modal */
.modal-overlay {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.7);
  display: flex;
  justify-content: center;
  align-items: center;
  z-index: 100;
}

.modal-content {
  position: relative;
  width: 80%;
  max-width: 600px;
  background-color: white;
  padding: 20px;
  border-radius: 10px;
}

.rate-button {
  background-color: #f5c518;
  color: black;
  font-size: 16px;
  padding: 10px 20px;
  border: none;
  cursor: pointer;
  border-radius: 5px;
}

.rate-button:hover {
  background-color: #e4b400;
}

/* Styles for Trailer Modal */
.modal-overlay {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.7);
  display: flex;
  justify-content: center;
  align-items: center;
  z-index: 100;
}

.modal-content {
  position: relative;
  width: 80%;
  height: 230px;
  max-width: 600px;
  background-color: white;
  padding: 20px;
  border-radius: 10px;
}

.modal-content-trailer {
  position: relative;
  width: 80%;
  height: 440px;
  max-width: 800px;
  background-color: white;
  padding: 20px;
  border-radius: 10px;
}

.close-button {
  position: absolute;
  top: 10px;
  right: 10px;
  background-color: red;
  color: white;
  font-size: 1.2rem;
  border: none;
  padding: 10px;
  cursor: pointer;
  border-radius: 5px;
}

.close-button:hover {
  background-color: darkred;
}

.trailer-button {
  background-color: #007bff;
  color: white;
  font-size: 16px;
  padding: 10px 20px;
  border: none;
  cursor: pointer;
  border-radius: 5px;
}

.trailer-button:hover {
  background-color: #0056b3;
}
</style>
