<template>
  <div style="padding: 24px">
    <p style="padding-top: 80px; padding-bottom: 20px; font-size: 3rem">
      {{ category.charAt(0).toUpperCase() + category.slice(1) }} Movies
    </p>
    <CardCagegoryList :selected-category="category" />
  </div>
</template>

<script setup>
import { useRoute } from 'vue-router'
import CardCagegoryList from './card/CardCagegoryList.vue'

const route = useRoute()
const category = route.params.category || 'popular' // Default to 'popular' if no category is selected
</script>
