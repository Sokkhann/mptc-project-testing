package mptc.co.movie_rating.freature.review;

import lombok.AllArgsConstructor;
import mptc.co.movie_rating.domain.Review;
import mptc.co.movie_rating.freature.review.dto.ReviewRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    // Submit a review
    public Review submitReview(ReviewRequest reviewRequest) {
        Review review = new Review();
        review.setAnonymousUserId(reviewRequest.getAnonymousUserId());
        review.setMovieId(reviewRequest.getMovieId());
        review.setText(reviewRequest.getText());
        review.setUsername(reviewRequest.getUsername());
        review.setProfileImage(reviewRequest.getProfileImage());
        review.setCreateAt(new Date());
        return reviewRepository.save(review);
    }

    // Get all reviews for an anonymous user
    public List<Review> getReviewsByAnonymousUser(String anonymousUserId) {
        return reviewRepository.findByAnonymousUserId(anonymousUserId);
    }

    // Get all reviews for a specific movie
    public List<Review> getReviewsByMovie(String movieId) {
        return reviewRepository.findByMovieId(movieId);
    }
}