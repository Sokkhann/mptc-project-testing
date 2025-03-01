<!-- CardComponent.vue -->
<template>
  <n-card>
    <template #cover>
      <img :src="image" alt="Card Image" />
    </template>
  </n-card>
</template>

<script setup>
defineProps({
  title: String,
  image: String,
  content: String,
})
</script>

<style scoped>
img {
  height: 300px;
  object-fit: cover;
}
</style>
