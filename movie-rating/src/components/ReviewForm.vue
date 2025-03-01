<template>
  <div>
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

    <!-- Review Form -->
    <h2 style="font-size: 2rem; font-weight: bold; margin-bottom: 12px">Submit a Review</h2>
    <n-input
      type="textarea"
      v-model:value="reviewText"
      class="review-input"
      placeholder="Write your review here..."
      :autosize="{ minRows: 3 }"
      style="
        height: 200px;
        background-color: #1b1b1b;
        font-size: 1rem;
        --n-text-color: white;
        --n-placeholder-color: white;
      "
    />
    <div style="display: flex; justify-content: flex-end; margin-top: 12px; gap: 12px">
      <n-button type="error" size="large" @click="clearReview"> Cancel </n-button>
      <n-button @click="handleAddReview" size="large" type="success" :disabled="!canSubmit">
        Submit
      </n-button>
    </div>
  </div>
</template>

<script setup>
import { ref, computed } from 'vue'
import { submitReview } from '@/api/reviewApi'
import { useRoute } from 'vue-router'
import { getUserId } from '@/utils/userUtils'

const route = useRoute()
const reviewText = ref('')
const userInfo = ref(getUserId()) // Fetch the current user info

// Alert state
const showAlert = ref(false)
const alertTitle = ref('')
const alertType = ref('')
const message = ref('')

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

// Computed property to determine if the user can submit a review
const canSubmit = computed(() => {
  return userInfo.value.name.trim() !== '' // User must have a name to submit
})

// Handle adding a review
const handleAddReview = async () => {
  // Trim the input to remove leading/trailing whitespace
  const trimmedReviewText = reviewText.value.trim()

  // Validation: Check if the review text is empty or contains only whitespace
  if (!trimmedReviewText) {
    showAlertMessage('Validation Error', 'error', 'Review cannot be empty. Please write something.')
    return
  }

  // Validation: Check if the review text has more than 10 characters
  if (trimmedReviewText.length <= 10) {
    showAlertMessage('Validation Error', 'error', 'Your review must be longer than 10 characters.')
    return
  }

  // Ensure the user has a name before submitting
  if (!userInfo.value.name.trim()) {
    showAlertMessage(
      'Profile Incomplete',
      'warning',
      'Please provide your name before submitting a review.',
    )
    return
  }

  const movieId = route.params.id

  try {
    // Extract username and profileImage from userInfo
    const { anonymousUserId, name: username, profileImage } = userInfo.value

    // Attempt to submit the review
    await submitReview(anonymousUserId, movieId, trimmedReviewText, username, profileImage)

    // Clear the review text box
    reviewText.value = ''

    // Show success alert
    showAlertMessage('Success', 'success', 'Your review has been submitted successfully!')
    window.location.reload();
  } catch (error) {
    console.error('Error submitting review:', error)

    // Show error alert
    showAlertMessage('Error', 'error', 'Failed to submit your review. Please try again later.')
  }
}

// Clear the review text
const clearReview = () => {
  reviewText.value = ''
}
</script>

<style scoped>
/* Custom Styles for the Alert */
.custom-alert {
  position: fixed;
  top: 20px; /* Distance from the top of the screen */
  left: 50%; /* Center horizontally */
  transform: translateX(-50%); /* Adjust for exact centering */
  z-index: 9999; /* Ensure it appears above other content */
  width: 500px; /* Set a reasonable width */
  max-width: 90%; /* Ensure it doesn't exceed the screen width */
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1); /* Add a subtle shadow */
  border-radius: 8px; /* Rounded corners */
}
</style>
