package mptc.co.movie_rating.freature.favorite;

import mptc.co.movie_rating.domain.Favorite;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FavoriteService {

    private final FavoriteRepository favoriteRepository;

    public FavoriteService(FavoriteRepository favoriteRepository) {
        this.favoriteRepository = favoriteRepository;
    }

    public boolean isFavorite(String anonymousUserId, String itemId) {
        Optional<Favorite> favorite = favoriteRepository.findByAnonymousUserIdAndItemId(anonymousUserId, itemId);
        return favorite.map(Favorite::getIsFavorite).orElse(false);
    }

    /**
     * Adds an item to the user's favorites.
     * @param anonymousUserId - The ID of the user.
     * @param itemId - The ID of the item.
     */
    public void addFavorite(String anonymousUserId, String itemId) {
        Optional<Favorite> existingFavorite = favoriteRepository.findByAnonymousUserIdAndItemId(anonymousUserId, itemId);

        if (existingFavorite.isPresent()) {
            // Update the existing record to mark it as favorited
            Favorite favorite = existingFavorite.get();
            favorite.setIsFavorite(true);
            favoriteRepository.save(favorite);
        } else {
            // Create a new favorite record
            Favorite favorite = new Favorite();
            favorite.setAnonymousUserId(anonymousUserId);
            favorite.setItemId(itemId);
            favorite.setIsFavorite(true);
            favoriteRepository.save(favorite);
        }
    }

    public List<Favorite> getAllFavoritesByUser(String anonymousUserId) {
        return favoriteRepository.findByAnonymousUserId(anonymousUserId);
    }

    public void removeFavorite(String anonymousUserId, String itemId) {
        // Delete the favorite record directly
        favoriteRepository.deleteByAnonymousUserIdAndItemId(anonymousUserId, itemId);
    }
}