package mptc.co.movie_rating.domain;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@Document(collection = "favorites")
public class Favorite {
    @Id
    private String id;
    private String anonymousUserId;
    private String itemId;
    private Boolean isFavorite;
}