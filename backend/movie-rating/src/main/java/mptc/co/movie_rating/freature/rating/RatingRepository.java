package mptc.co.movie_rating.freature.rating;

import mptc.co.movie_rating.domain.Rating;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface RatingRepository extends MongoRepository<Rating, String> {
    List<Rating> findByAnonymousUserId(String anonymousUserId); // Find ratings by user ID
    List<Rating> findByMovieId(String movieId); // Find ratings for a specific movie
}