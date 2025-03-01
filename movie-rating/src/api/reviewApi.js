// src/api/reviewsApi.js

import axios from 'axios'

export const fetchReviews = async (itemId) => {
  try {
    const response = await axios.get(`http://localhost:8080/api/reviews/movie/${itemId}`)
    return response.data
  } catch (error) {
    console.error('Error fetching reviews:', error)
    throw error
  }
}

export const submitReview = async (anonymousUserId, movieId, text, username, profileImage) => {
  try {
    await axios.post('http://localhost:8080/api/reviews', {
      anonymousUserId,
      movieId,
      text,
      username,
      profileImage,
    })
  } catch (error) {
    console.error('Error submitting review:', error)
    throw error
  }
}
