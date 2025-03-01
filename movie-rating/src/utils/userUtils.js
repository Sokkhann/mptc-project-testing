/**
 * Generates or retrieves the anonymous user ID and associated user info.
 * If an ID is already stored in localStorage, it returns that ID and any associated user info.
 * Otherwise, it generates a new unique ID, stores it in localStorage, and returns it.
 * @returns {object} An object containing the anonymous user ID, name, and profile image.
 */
export const getUserId = () => {
  // Try to retrieve the userInfo from localStorage
  let userInfo = localStorage.getItem('userInfo')

  // If userInfo exists, parse and return it
  if (userInfo) {
    const parsedUserInfo = JSON.parse(userInfo)
    console.log('Retrieved existing userInfo:', parsedUserInfo)
    return parsedUserInfo
  }

  // If no userInfo exists, generate a new one
  const anonymousUserId = crypto.randomUUID() // Generate a unique ID
  const defaultUserInfo = {
    anonymousUserId,
    name: '', // Empty by default
    profileImage: '', // Empty by default
  }

  // Store the default userInfo in localStorage
  localStorage.setItem('userInfo', JSON.stringify(defaultUserInfo))
  console.log('Generated new userInfo:', defaultUserInfo)

  return defaultUserInfo
}

/**
 * Updates the user's profile information (name and profile image).
 * @param {string} name - The user's name (required).
 * @param {string} [profileImage] - The user's profile image URL (optional).
 * @returns {object|null} The updated user info object, or null if the name is not provided.
 */
export const updateUserProfile = (name, profileImage = '') => {
  // Retrieve the current userInfo from localStorage
  let userInfo = localStorage.getItem('userInfo')

  // If no userInfo exists, exit with an error message
  if (!userInfo) {
    console.error('No user info found in localStorage. Please call getUserId first.')
    return null
  }

  // Parse the userInfo
  const parsedUserInfo = JSON.parse(userInfo)

  // Validate that the name is provided
  if (!name) {
    console.error('Name is required to update the user profile.')
    return null
  }

  // Update the userInfo with the new name and optional profile image
  parsedUserInfo.name = name
  parsedUserInfo.profileImage = profileImage || '' // Default to empty string if not provided

  // Store the updated userInfo back in localStorage
  localStorage.setItem('userInfo', JSON.stringify(parsedUserInfo))
  console.log('Updated userInfo:', parsedUserInfo)

  return parsedUserInfo
}
