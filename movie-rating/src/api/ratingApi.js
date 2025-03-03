import axios from 'axios'
const BASE_URL = import.meta.env.VITE_API_BASE_URL || 'http://localhost:8080'
export const submitRating = async (anonymousUserId, movieId, rating) => {
  try {
    await axios.post(`${BASE_URL}/api/ratings`, {
      anonymousUserId,
      movieId,
      rating,
    })
  } catch (error) {
    console.error('Error submitting rating:', error)
    throw error
  }
}
