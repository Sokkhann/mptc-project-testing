package mptc.co.movie_rating.domain;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "items")
public class Item {
    @Id
    private String id;
    private String title;
    private String description;
    private String imageUrl;
    private Boolean isFavorite;
}