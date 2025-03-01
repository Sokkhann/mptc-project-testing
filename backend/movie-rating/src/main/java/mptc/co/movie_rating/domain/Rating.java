package mptc.co.movie_rating.domain;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "ratings")
public class Rating {
    @Id
    private String id;
    private String anonymousUserId;
    private String movieId;
    private int rating; // e.g., 1 to 5
}