package mptc.co.movie_rating.freature.review;

import mptc.co.movie_rating.domain.Review;
import mptc.co.movie_rating.freature.review.dto.ReviewRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
@CrossOrigin("/http/")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    // Submit a review
    @PostMapping
    public ResponseEntity<Review> submitReview(@RequestBody ReviewRequest reviewRequest) {
        Review review = reviewService.submitReview(reviewRequest);
        return ResponseEntity.ok(review);
    }

    // Get all reviews for an anonymous user
    @GetMapping("/user/{anonymousUserId}")
    public ResponseEntity<List<Review>> getReviewsByAnonymousUser(@PathVariable String anonymousUserId) {
        List<Review> reviews = reviewService.getReviewsByAnonymousUser(anonymousUserId);
        return ResponseEntity.ok(reviews);
    }

    // Get all reviews for a specific movie
    @GetMapping("/movie/{movieId}")
    public ResponseEntity<List<Review>> getReviewsByMovie(@PathVariable String movieId) {
        List<Review> reviews = reviewService.getReviewsByMovie(movieId);
        return ResponseEntity.ok(reviews);
    }
}