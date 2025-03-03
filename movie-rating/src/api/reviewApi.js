// src/api/reviewsApi.js

import axios from 'axios'

const BASE_URL = import.meta.env.VITE_API_BASE_URL || 'http://localhost:8080'

export const fetchReviews = async (itemId) => {
  try {
    const response = await axios.get(`${BASE_URL}/api/reviews/movie/${itemId}`)
    return response.data
  } catch (error) {
    console.error('Error fetching reviews:', error)
    throw error
  }
}

export const submitReview = async (anonymousUserId, movieId, text, username, profileImage) => {
  try {
    await axios.post(`${BASE_URL}/api/reviews`, {
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
