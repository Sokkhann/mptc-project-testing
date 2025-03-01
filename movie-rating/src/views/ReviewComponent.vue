// ReviewComponent.vue
<template>
  <div class="review-container">
    <ReviewForm @add-review="addReview" class="review-form" />
    <ReviewList :reviews="reviews" class="review-list" />
  </div>
</template>

<script>
import ReviewForm from '@/components/ReviewForm.vue'
import ReviewList from '@/components/ReviewList.vue'

export default {
  components: { ReviewForm, ReviewList },
  data() {
    return {
      reviews: [],
    }
  },
  methods: {
    addReview(review) {
      if (review.trim()) {
        this.reviews.push(review)
      }
    },
  },
}
</script>

<style scoped>
.review-container {
  display: flex;
  flex-direction: row;
  padding: 12px;
}

.review-form {
  width: 100%;
  padding: 16px;
}

.review-list {
  width: 100%;
  padding: 16px;
}
</style>
