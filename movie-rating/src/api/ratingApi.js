import axios from 'axios'

export const submitRating = async (anonymousUserId, movieId, rating) => {
  try {
    await axios.post('http://localhost:8080/api/ratings', {
      anonymousUserId,
      movieId,
      rating,
    })
  } catch (error) {
    console.error('Error submitting rating:', error)
    throw error
  }
}
