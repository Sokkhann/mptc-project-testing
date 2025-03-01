package mptc.co.movie_rating.freature.rating;

import lombok.AllArgsConstructor;
import mptc.co.movie_rating.domain.Rating;
import mptc.co.movie_rating.freature.rating.dto.RatingRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RatingService {

    @Autowired
    private RatingRepository ratingRepository;

    // Submit a rating
    public Rating submitRating(RatingRequest ratingRequest) {
        Rating newRating = new Rating();
        newRating.setAnonymousUserId(ratingRequest.getAnonymousUserId());
        newRating.setMovieId(ratingRequest.getMovieId());
        newRating.setRating(ratingRequest.getRating());
        return ratingRepository.save(newRating);
    }

    // Get all ratings for a user
    public List<Rating> getRatingsByUser(String userId) {
        return ratingRepository.findByAnonymousUserId(userId);
    }

    // Get all ratings for a specific movie
    public List<Rating> getRatingsByMovie(String movieId) {
        return ratingRepository.findByMovieId(movieId);
    }
}