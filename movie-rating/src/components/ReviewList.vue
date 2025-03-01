<template>
  <div>
    <!-- Title -->
    <h2 style="margin-bottom: 16px; font-size: 2rem; font-weight: bold">Reviews</h2>

    <!-- Scrollable Area -->
    <n-scrollbar y-scrollable style="max-height: 500px; margin-top: -4px; border-radius: 4px">
      <div class="scroll-container">
        <div class="card-container">
          <!-- Conditional Rendering: Show "No reviews yet" if no reviews exist -->
          <div v-if="reviews.length === 0" class="no-reviews-message">No reviews yet.</div>

          <!-- List of Reviews -->
          <n-list
            v-else
            bordered
            style="max-width: 100%; height: 80%; margin: 0; border-radius: 4px"
          >
            <n-list-item v-for="(review, index) in reviews" :key="index">
              <div style="display: flex; align-items: flex-start; gap: 12px">
                <!-- Avatar -->
                <n-avatar round :src="review.profileImage" size="large" />
                <!-- Review Content -->
                <div>
                  <n-text style="font-weight: bold; font-size: 1rem">{{
                    review.username || 'Annoymous'
                  }}</n-text>
                  <n-text depth="" style="display: block; font-size: 0.8rem">
                    {{ review.date }}
                  </n-text>
                </div>
              </div>
              <n-text style="display: block; margin-top: 10px">
                {{ review.text }}
              </n-text>
            </n-list-item>
          </n-list>
        </div>
      </div>
    </n-scrollbar>
  </div>
</template>

<script>
import { NList, NListItem, NAvatar, NText } from 'naive-ui'
import { ref, onMounted } from 'vue'
import { useRoute } from 'vue-router' // Import useRoute for route parameters
import { fetchReviews } from '@/api/reviewApi' // Import the fetchReviews function

export default {
  components: {
    NList,
    NListItem,
    NAvatar,
    NText,
  },
  setup() {
    const reviews = ref([]) // Initialize reviews as an empty array
    const route = useRoute() // Access the current route

    // Helper function to safely format the date
    const formatDate = (isoDate) => {
      if (!isoDate) {
        console.warn('Missing or null date value:', isoDate)
        return 'Unknown Date' // Fallback for missing dates
      }
      const date = new Date(isoDate)
      if (isNaN(date.getTime())) {
        console.error('Invalid date received:', isoDate)
        return 'Unknown Date' // Fallback for invalid dates
      }
      const options = { month: 'short', day: 'numeric', year: 'numeric' }
      return date.toLocaleDateString('en-US', options)
    }

    // Function to load reviews for a specific item
    // Function to load reviews for a specific item
    const loadReviews = async (itemId) => {
      try {
        const fetchedReviews = await fetchReviews(itemId)
        // Log the raw response for debugging
        console.log('Fetched Reviews:', fetchedReviews)

        // Sort reviews by createdAt in descending order (latest first)
        reviews.value = fetchedReviews
          .map((review) => ({
            ...review,
            date: formatDate(review.createAt), // Format the date here
          }))
          .sort((a, b) => new Date(b.createAt) - new Date(a.createAt)) // Sort by createdAt
      } catch (error) {
        console.error('Failed to load reviews:', error)
      }
    }

    // Lifecycle hook to fetch reviews when the component is mounted
    onMounted(() => {
      const itemId = route.params.id // Get the itemId from the route parameters
      if (itemId) {
        loadReviews(itemId) // Load reviews for the specified item
      } else {
        console.error('Item ID is missing in the route parameters.')
      }
    })

    return {
      reviews, // Expose reviews to the template
    }
  },
}
</script>

<style scoped>
/* Custom Styles */
.n-list {
  border-radius: 8px;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
}
.n-avatar {
  flex-shrink: 0;
}
.n-text {
  line-height: 1.5;
}

/* Style for the "No reviews yet" message */
.no-reviews-message {
  text-align: center;
  font-size: 1.2rem;
  color: #666;
  padding: 20px;
  border: 1px dashed #ccc;
  border-radius: 8px;
  margin: 0 0;
}
</style>
