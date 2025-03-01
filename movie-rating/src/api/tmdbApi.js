import axios from 'axios'

// TMDB API Configuration
const TMDB_API_KEY = import.meta.env.VITE_TMDB_API_KEY
const BASE_URL = 'https://api.themoviedb.org/3'
const IMAGE_BASE_URL = 'https://image.tmdb.org/t/p/w500' // Base URL for images

/**
 * Fetches detailed information about a movie from TMDB.
 * @param {string} movieId - The ID of the movie to fetch.
 * @returns {Promise<object>} An object containing the movie's image, title, description, and genre.
 */
export const getMovieDetails = async (movieId) => {
  try {
    const response = await axios.get(`${BASE_URL}/movie/${movieId}`, {
      params: {
        api_key: TMDB_API_KEY,
        language: 'en-US', // Optional: Specify language
      },
    })

    const movieData = response.data

    // Extract only the required fields
    const movieDetails = {
      id: movieData.id,
      title: movieData.title || 'Untitled Movie',
      description: movieData.overview || 'No description available.',
      image: movieData.poster_path ? `${IMAGE_BASE_URL}${movieData.poster_path}` : null,
      genre: movieData.genres.map((genre) => genre.name).join(', ') || 'Unknown Genre',
    }

    return movieDetails
  } catch (error) {
    console.error('Error fetching movie details from TMDB:', error)
    throw error
  }
}

/**
 * Searches for movies based on a query.
 * @param {string} query - The search query (e.g., movie title).
 * @returns {Promise<Array>} A list of movies matching the query, with only the required fields.
 */
export const searchMovies = async (query) => {
  try {
    const response = await axios.get(`${BASE_URL}/search/movie`, {
      params: {
        api_key: TMDB_API_KEY,
        query: query,
        language: 'en-US', // Optional: Specify language
      },
    })

    const results = response.data.results

    // Map the results to include only the required fields
    return results.map((movie) => ({
      id: movie.id,
      title: movie.title || 'Untitled Movie',
      description: movie.overview || 'No description available.',
      image: movie.poster_path ? `${IMAGE_BASE_URL}${movie.poster_path}` : null,
      genre: 'Unknown Genre', // Genres are not included in search results; you may need to fetch them separately
    }))
  } catch (error) {
    console.error('Error searching movies on TMDB:', error)
    throw error
  }
}
