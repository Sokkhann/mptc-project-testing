<template>
  <p style="color: black; font-size: 1.3rem">Rate the movie here with us ✨</p>
  <div class="rating-container">
    <!-- Alert Component -->
    <n-alert
      class="custom-alert"
      v-if="showAlert"
      :title="alertTitle"
      :type="alertType"
      closable
      @close="hideAlert"
    >
      {{ message }}
    </n-alert>
    <span
      v-for="(star, index) in 10"
      :key="index"
      class="star"
      @click="setRating(index + 1)"
      @mousemove="previewRating(index + 1, $event)"
      @mouseleave="resetPreview"
    >
      <font-awesome-icon
        v-if="getStarType(index) === 'full'"
        icon="fa-solid fa-star"
        class="filled"
      />
      <font-awesome-icon
        v-else-if="getStarType(index) === 'half'"
        icon="fa-solid fa-star-half-stroke"
        class="filled"
      />
      <font-awesome-icon v-else icon="fa-regular fa-star" class="empty" />
    </span>
  </div>
  <!-- Display selected rating -->
  <p class="rating-value">
    Your Rating is:
    <span v-if="rating > 0" class="rating-value">{{ rating }}</span>
  </p>
  <!-- Button for submit -->
  <n-button style="margin-top: 24px" type="primary" @click="handleSubmit" :disabled="!canSubmit">
    Submit
  </n-button>
</template>

<script setup>
import { ref, computed } from 'vue'
import { useRoute } from 'vue-router'
import { submitRating } from '@/api/ratingApi' // Import the submitRating function
import { getUserId } from '@/utils/userUtils' // Import getUserId utility

const rating = ref(0) // Stores final rating
const tempRating = ref(0) // Stores preview when hovering
const route = useRoute() // Access route parameters
const userInfo = ref(getUserId()) // Fetch user info

// Alert state
const showAlert = ref(false)
const alertTitle = ref('')
const alertType = ref('')
const message = ref('')

// Computed property to determine if the user can submit a rating
const canSubmit = computed(() => {
  return rating.value > 0 && userInfo.value.name.trim() !== '' // User must have a name and a rating
})

// Function to show an alert
const showAlertMessage = (title, type, msg) => {
  alertTitle.value = title
  alertType.value = type
  message.value = msg
  showAlert.value = true
}

// Function to hide the alert
const hideAlert = () => {
  showAlert.value = false
}

// Function to set the final rating
const setRating = (value) => {
  rating.value = value - 0.5 // Adjust for half-star selection
}

// Function to preview the rating on hover
const previewRating = (value, event) => {
  const isHalf = event.offsetX < event.target.clientWidth / 2
  tempRating.value = isHalf ? value - 0.5 : value
}

// Function to reset the preview when leaving the stars
const resetPreview = () => {
  tempRating.value = 0
}

// Function to determine the star type (full, half, or empty)
const getStarType = (index) => {
  const currentRating = tempRating.value || rating.value
  if (index + 1 <= currentRating) return 'full'
  if (index + 0.5 === currentRating) return 'half'
  return 'empty'
}

// Handle the submission of the rating
const handleSubmit = async () => {
  // Ensure the user has provided their name
  if (!userInfo.value.name.trim()) {
    showAlertMessage(
      'Profile Incomplete',
      'warning',
      'Please provide your name before submitting a rating.',
    )
    return
  }

  try {
    const anonymousUserId = userInfo.value.anonymousUserId // Get the user ID
    const movieId = route.params.id // Get the movie ID from the route

    if (!movieId) {
      console.error('Movie ID is missing in the route parameters.')
      return
    }

    // Call the submitRating API
    await submitRating(anonymousUserId, movieId, rating.value)

    // Optionally, show a success message
    showAlertMessage('Success', 'success', 'Your rating has been submitted successfully!')
  } catch (error) {
    console.error('Error submitting rating:', error)
    showAlertMessage('Error', 'error', 'Failed to submit your rating. Please try again later.')
  }
}
</script>

<style scoped>
.rating-container {
  display: flex;
  gap: 25px;
  cursor: pointer;
}
.star {
  font-size: 30px;
}
.filled {
  color: gold;
}
.empty {
  color: gray;
}
.rating-value {
  margin-top: 10px;
  font-size: 1.5rem;
  color: black;
  font-weight: bold;
}
/* Custom Styles for the Alert */
.custom-alert {
  position: fixed;
  top: 100px; /* Distance from the top of the screen */
  left: 50%; /* Center horizontally */
  transform: translateX(-50%); /* Adjust for exact centering */
  z-index: 100000; /* Ensure it appears above other content */
  width: 500px; /* Set a reasonable width */
  max-width: 90%; /* Ensure it doesn't exceed the screen width */
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1); /* Add a subtle shadow */
  border-radius: 8px; /* Rounded corners */
}
</style>
