package mptc.co.movie_rating.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Document(collection = "reviews")
public class Review {
    @Id
    private String id;
    private String anonymousUserId;
    private String movieId;
    private String text;
    private String username;
    private String profileImage;
    private Date createAt;
}