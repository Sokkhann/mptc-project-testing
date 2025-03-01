<template>
  <font-awesome-icon
    :icon="isFavorite ? 'fa-solid fa-bookmark' : 'fa-regular fa-bookmark'"
    size="2x"
    :color="isFavorite ? 'yellow' : 'gray'"
    @click="toggleFavorite"
    style="cursor: pointer"
  />
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { useRoute } from 'vue-router' // Import useRoute for route parameters
import { checkFavorite, addFavorite, removeFavorite } from '@/api/favoriteApi'
import { getUserId } from '@/utils/userUtils'

// Get the anonymous user ID from localStorage or generate one
const userInfo = getUserId()
const anonymousUserId = userInfo.anonymousUserId

// Get the itemId from the route parameters
const route = useRoute()
const itemId = route.params.id // Assuming the itemId is passed as `id` in the route

// State to track if the item is favorited
const isFavorite = ref(false)

// Check if the item is already favorited when the component loads
onMounted(async () => {
  await checkIfFavorited()
})

// Check if the item is favorited using the API
async function checkIfFavorited() {
  try {
    const favoriteStatus = await checkFavorite(anonymousUserId, itemId)
    isFavorite.value = favoriteStatus // Update the favorite status
  } catch (error) {
    console.error('Error checking favorite status:', error)
  }
}

// Toggle the favorite status
async function toggleFavorite() {
  try {
    if (isFavorite.value) {
      // Remove from favorites
      await removeFavorite(anonymousUserId, itemId)
    } else {
      // Add to favorites
      await addFavorite(anonymousUserId, itemId)
    }
    // Toggle the state locally
    isFavorite.value = !isFavorite.value
  } catch (error) {
    console.error('Error toggling favorite:', error)
  }
}
</script>

<style scoped>
/* Add any styles here if needed */
</style>
