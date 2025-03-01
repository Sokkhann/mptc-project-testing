<template>
  <!-- navbar sectoin -->
  <NavbarComponent />

  <router-view />
</template>

<script>
import { defineComponent } from 'vue'
import NavbarComponent from './components/NavbarComponent.vue'

export default defineComponent({
  components: { NavbarComponent },
})
</script>

<style></style>
