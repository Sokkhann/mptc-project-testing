package mptc.co.movie_rating.freature.rating;


import lombok.AllArgsConstructor;
import mptc.co.movie_rating.domain.Rating;
import mptc.co.movie_rating.freature.rating.dto.RatingRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ratings")
@AllArgsConstructor
public class RatingController {

    private RatingService ratingService;

    // Submit a rating
    @PostMapping
    public ResponseEntity<Rating> submitRating(@RequestBody RatingRequest ratingRequest) {
        Rating savedRating = ratingService.submitRating(ratingRequest);
        return ResponseEntity.ok(savedRating);
    }

    // Get all ratings for a user
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Rating>> getRatingsByUser(@PathVariable String userId) {
        List<Rating> ratings = ratingService.getRatingsByUser(userId);
        return ResponseEntity.ok(ratings);
    }

    // Get all ratings for a specific movie
    @GetMapping("/movie/{movieId}")
    public ResponseEntity<List<Rating>> getRatingsByMovie(@PathVariable String movieId) {
        List<Rating> ratings = ratingService.getRatingsByMovie(movieId);
        return ResponseEntity.ok(ratings);
    }
}