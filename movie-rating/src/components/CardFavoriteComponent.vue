<template>
  <div>
    <!-- Title -->
    <h2
      style="
        font-size: 2rem;
        font-weight: bold;
        padding-top: 100px;
        padding-left: 300px;
        margin-bottom: 16px;
      "
    >
      My Favorites
    </h2>

    <!-- Favorite List -->
    <n-scrollbar y-scrollable style="max-height: 500px; border-radius: 8px">
      <div v-if="favorites.length > 0" class="favorite-list">
        <div v-for="(favorite, index) in favorites" :key="index" class="favorite-item">
          <!-- Left Section: Movie Image -->
          <router-link :to="`/movie/${favorite.id}`" class="favorite-image-link">
            <div class="favorite-image">
              <img
                :src="favorite.image || 'https://via.placeholder.com/150'"
                alt="Movie Poster"
                style="width: 150px; height: 200px; object-fit: cover; border-radius: 8px"
              />
            </div>
          </router-link>

          <!-- Right Section: Movie Details -->
          <div class="favorite-details">
            <h3 style="font-size: 1.2rem; font-weight: bold">{{ favorite.title }}</h3>
            <p style="font-size: 0.9rem; color: gray; margin: 4px 0">
              <strong>Genre:</strong> {{ favorite.genre }}
            </p>
            <p style="font-size: 0.9rem; margin: 4px 0">{{ favorite.description }}</p>
          </div>
        </div>
      </div>

      <!-- No Favorites Message -->
      <div v-else class="no-favorites">
        <p style="text-align: center; font-size: 1.2rem; color: gray">
          You haven't added any favorites yet.
        </p>
      </div>
    </n-scrollbar>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { getFavoritesByUser } from '@/api/favoriteApi'
import { getUserId } from '@/utils/userUtils'
import { getMovieDetails } from '@/api/tmdbApi'

// Reactive state for favorites
const favorites = ref([])

// Fetch all favorites for the user and enrich them with TMDB data
const fetchFavorites = async () => {
  try {
    // Get the current user's ID
    const userInfo = getUserId()
    const { anonymousUserId } = userInfo

    // Fetch the list of favorite items
    const favoriteItems = await getFavoritesByUser(anonymousUserId)

    // Fetch detailed movie data for each favorite item
    favorites.value = await Promise.all(
      favoriteItems.map(async (item) => {
        const movieDetails = await getMovieDetails(item.itemId) // Fetch details from TMDB
        return movieDetails
      }),
    )
  } catch (error) {
    console.error('Error fetching favorites or movie details:', error)
  }
}

// Fetch favorites when the component is mounted
onMounted(() => {
  fetchFavorites()
})
</script>

<style scoped>
/* Favorite List Container */
.favorite-list {
  padding-left: 300px;
  padding-right: 300px;
  display: flex;
  flex-direction: column;
  gap: 16px;
}

/* Favorite Item */
.favorite-item {
  display: flex;
  align-items: flex-start;
  gap: 16px;
  padding: 12px;
  background-color: #1b1b1b;
  border-radius: 8px;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
}

/* Favorite Image */
.favorite-image {
  flex-shrink: 0;
}

/* Favorite Details */
.favorite-details {
  flex-grow: 1;
}

/* No Favorites Message */
.no-favorites {
  text-align: center;
  padding: 24px;
  background-color: #1b1b1b;
  border-radius: 8px;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
}
</style>
