<template>
  <n-card style="background-color: #1b1b1b">
    <template #cover>
      <img :src="image" alt="Card Image" />
    </template>
    <div
      style="
        font-size: 1.2rem;
        font-weight: bold;
        white-space: nowrap;
        overflow: hidden;
        text-overflow: ellipsis;
        color: whitesmoke;
        padding-top: 12px;
      "
    >
      {{ title }}
    </div>
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
  height: 600px;
  object-fit: cover; /* Ensure the image covers the area without stretching */
}
</style>
