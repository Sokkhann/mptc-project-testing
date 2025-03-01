package mptc.co.movie_rating.freature.favorite.dto;


import lombok.Data;

@Data // Lombok annotation to generate getters, setters, toString, etc.
public class FavoriteRequest {

    private String anonymousUserId; // Unique identifier for the user
    private String itemId; // ID of the item (e.g., movie ID)

    @Override
    public String toString() {
        return "FavoriteRequest{" +
                "anonymousUserId='" + anonymousUserId + '\'' +
                ", itemId='" + itemId + '\'' +
                '}';
    }
}