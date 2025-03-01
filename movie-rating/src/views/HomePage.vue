<template>
  <div>
    <!-- carousel section -->
    <CarouselComponent />

    <div class="icon-container">
      <h1>Latest Updat</h1>
      <router-link :to="'/popular'">
        <font-awesome-icon icon="arrow-right" size="2x" />
      </router-link>
    </div>
    <CardList :selected-category="'popular'" />

    <div class="icon-container">
      <h1>Romance</h1>
      <router-link :to="'/romance'">
        <font-awesome-icon icon="arrow-right" size="2x" />
      </router-link>
    </div>
    <CardList :selected-category="'romance'" />

    <div class="icon-container">
      <h1>Comedy</h1>
      <router-link :to="'/comedy'">
        <font-awesome-icon icon="arrow-right" size="2x" />
      </router-link>
    </div>
    <CardList :selected-category="'comedy'" />

    <div class="icon-container">
      <h1>Anime</h1>
      <router-link :to="'/anime'">
        <font-awesome-icon icon="arrow-right" size="2x" />
      </router-link>
    </div>
    <CardList :selected-category="'anime'" />

    <div class="icon-container">
      <h1>Hollywood</h1>
      <router-link :to="'/hollywood'">
        <font-awesome-icon icon="arrow-right" size="2x" />
      </router-link>
    </div>
    <CardList :selected-category="'hollywood'" />
  </div>
</template>

<script>
import CarouselComponent from '@/components/CarouselComponent.vue'
import CardList from './card/CardList.vue'

export default {
  components: { CardList, CarouselComponent },
}
</script>

<style>
h1 {
  padding: 24px;
}
.icon-container {
  display: flex;
  align-items: center; /* Centers vertically */
  height: 100px; /* Set a height for the container */
}
</style>
