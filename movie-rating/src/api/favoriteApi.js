import axios from 'axios'
const BASE_URL = import.meta.env.VITE_API_BASE_URL || 'http://localhost:8080'

/**
 * Checks if an item is marked as a favorite by the user.
 * @param {string} anonymousUserId - The ID of the user.
 * @param {string} itemId - The ID of the item to check.
 * @returns {Promise<boolean>} - Returns `true` if the item is a favorite, otherwise `false`.
 */
export const checkFavorite = async (anonymousUserId, itemId) => {
  try {
    const response = await axios.get(
      `${BASE_URL}/api/favorites/check/${anonymousUserId}/${itemId}`,
    )
    return response.data.isFavorite // Assumes the backend returns { isFavorite: true/false }
  } catch (error) {
    console.error('Error checking favorite status:', error)
    throw error
  }
}

/**
 * Adds an item to the user's favorites.
 * @param {string} anonymousUserId - The ID of the user.
 * @param {string} itemId - The ID of the item to add as a favorite.
 * @returns {Promise<void>}
 */
export const addFavorite = async (anonymousUserId, itemId) => {
  try {
    await axios.post(`${BASE_URL}/api/favorites/${anonymousUserId}/${itemId}`)
  } catch (error) {
    console.error('Error adding favorite:', error)
    throw error
  }
}

/**
 * Removes an item from the user's favorites.
 * @param {string} anonymousUserId - The ID of the user.
 * @param {string} itemId - The ID of the item to remove from favorites.
 * @returns {Promise<void>}
 */
export const removeFavorite = async (anonymousUserId, itemId) => {
  try {
    await axios.delete(`${BASE_URL}/api/favorites/${anonymousUserId}/${itemId}`)
  } catch (error) {
    console.error('Error removing favorite:', error)
    throw error
  }
}

/**
 * Fetches all favorited items for a specific user.
 * @param {string} anonymousUserId - The ID of the user.
 * @returns {Promise<Array>} - A list of favorited items.
 */
export const getFavoritesByUser = async (anonymousUserId) => {
  try {
    const response = await axios.get(`${BASE_URL}/api/favorites/${anonymousUserId}`)
    return response.data
  } catch (error) {
    console.error('Error fetching favorites:', error)
    throw error
  }
}
