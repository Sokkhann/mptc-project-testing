<template>
  <n-layout-header bordered style="padding: 20px; display: flex; align-items: center">
    <!-- Logo or Brand Name -->
    <div style="flex: 1">
      <router-link to="/" custom v-slot="{ navigate }">
        <n-button text @click="navigate"> <strong>S.K Rating</strong></n-button>
      </router-link>
    </div>

    <!-- Navigation Links -->
    <n-space>
      <router-link to="/favorite" custom v-slot="{ navigate }">
        <n-button text @click="navigate">Favorite</n-button>
      </router-link>
      <router-link to="/login" custom v-slot="{ navigate }">
        <n-button text @click="navigate">Create Account</n-button>
      </router-link>
    </n-space>
  </n-layout-header>
</template>

<script>
import { defineComponent } from 'vue'

export default defineComponent({
  setup() {
    const dropdownOptions = [
      { label: 'Profile', key: 'profile' },
      { label: 'Settings', key: 'settings' },
      { label: 'Logout', key: 'logout' },
    ]

    const handleDropdownSelect = (key) => {
      console.log(`Selected: ${key}`)
      // Add logic for handling dropdown actions here
    }

    return {
      dropdownOptions,
      handleDropdownSelect,
    }
  },
})
</script>

<style scoped>
.n-layout-header {
  /* Fixed Positioning */
  position: fixed;
  top: 4;
  left: 4;
  z-index: 1000; /* Ensure the navbar stays above other content */

  /* Glassmorphism Effect */
  background: rgba(255, 255, 255, 0.2); /* Semi-transparent background */
  backdrop-filter: blur(10px); /* Blurs the background behind the navbar */
  border-radius: 16px; /* Rounded corners */
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1); /* Subtle shadow for depth */

  width: 97%;
  margin: 24px;
}

/* Optional: Style for buttons */
.n-button {
  color: white; /* Text color for better visibility */
}
</style>
