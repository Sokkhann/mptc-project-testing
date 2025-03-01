<template>
  <n-card title="Update Your Profile" style="background-color: whitesmoke">
    <n-form @submit.prevent="handleSubmit">
      <!-- Name Input -->
      <n-form-item label="Name" size="large">
        <n-input type="text" v-model:value="name" placeholder="Enter your name" required />
      </n-form-item>

      <!-- Profile Image Input -->
      <n-form-item label="Profile Image URL (Optional)" size="large">
        <n-input type="url" v-model:value="profileImage" placeholder="Enter image URL" />
      </n-form-item>

      <!-- Submit Button -->
      <n-button type="primary" size="large" block @click="handleSubmit"> Update Profile </n-button>

      <!-- Success/Error Message -->
      <p v-if="message" :class="messageClass">{{ message }}</p>
    </n-form>
  </n-card>
</template>

<script>
import { ref } from 'vue'
import { useRouter } from 'vue-router' // Import Vue Router
import { updateUserProfile } from '@/utils/userUtils' // Import your updateUserProfile function

export default {
  setup() {
    const name = ref('') // Bind to the name input
    const router = useRouter() // Initialize the router
    const profileImage = ref('') // Bind to the profile image input
    const message = ref('') // To display success/error messages

    // Dynamically set the message class based on the message content

    // Handle form submission
    const handleSubmit = async () => {
      // Call the updateUserProfile function to update the user's info
      const updatedUserInfo = updateUserProfile(name.value, profileImage.value)
      if (updatedUserInfo) {
        message.value = 'Profile updated successfully!'

        // Navigate to the home page after a short delay
        setTimeout(() => {
          router.push('/') // Replace '/' with your home route path
        }, 1000) // 1-second delay before navigation
      } else {
        message.value = 'Failed to update profile. Please try again.'
      }
    }

    return {
      name,
      profileImage,
      message,
      handleSubmit,
    }
  },
}
</script>

<style scoped>
.n-card {
  max-width: 400px;
  margin: 20px auto;
}

.success-message {
  color: green;
  font-weight: bold;
}

.error-message {
  color: red;
  font-weight: bold;
}
</style>
