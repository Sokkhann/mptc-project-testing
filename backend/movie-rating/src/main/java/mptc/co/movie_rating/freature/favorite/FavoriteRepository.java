package mptc.co.movie_rating.freature.favorite;


import mptc.co.movie_rating.domain.Favorite;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FavoriteRepository extends MongoRepository<Favorite, String> {
    // Find all favorites for a specific user
    List<Favorite> findByAnonymousUserId(String anonymousUserId);

    // Find a favorite by user ID and item ID
    Optional<Favorite> findByAnonymousUserIdAndItemId(String anonymousUserId, String itemId);

    // Delete a favorite by user ID and item ID
    void deleteByAnonymousUserIdAndItemId(String anonymousUserId, String itemId);
}