package mptc.co.movie_rating.freature.review.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ReviewRequest {

    @NotBlank(message = "Anonymous User ID is required")
    private String anonymousUserId;

    @NotBlank(message = "Movie ID is required")
    private String movieId;

    @NotBlank(message = "Username is required")
    private String username;

    private String profileImage;

    @NotBlank(message = "Review text is required")
    private String text;
}