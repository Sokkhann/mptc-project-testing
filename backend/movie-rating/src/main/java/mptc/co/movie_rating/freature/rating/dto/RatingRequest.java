package mptc.co.movie_rating.freature.rating.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RatingRequest {

    @NotBlank(message = "User ID cannot be blank")
    private String anonymousUserId; // Anonymous user ID

    @NotBlank(message = "Movie ID cannot be blank")
    private String movieId;

    @Min(value = 1, message = "Rating must be at least 1")
    @Max(value = 10, message = "Rating cannot exceed 10")
    private int rating;
}