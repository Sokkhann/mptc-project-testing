package mptc.co.movie_rating.freature.review;

import mptc.co.movie_rating.domain.Review;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ReviewRepository extends MongoRepository<Review, String> {
    List<Review> findByAnonymousUserId(String anonymousUserId); // Find reviews by anonymous user ID
    List<Review> findByMovieId(String movieId); // Find reviews for a specific movie
}
