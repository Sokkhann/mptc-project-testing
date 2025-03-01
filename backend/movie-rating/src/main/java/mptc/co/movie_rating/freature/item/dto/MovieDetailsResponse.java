package mptc.co.movie_rating.freature.item.dto;

import lombok.Data;

import java.util.List;

@Data
public class MovieDetailsResponse {
    private int id;
    private List<Genre> genres;
    private String homepage;
    private String originalTitle;
    private String overview;
    private double popularity;
    private String posterPath;

    // Nested Genre class
    @Data
    public static class Genre {
        private int id;
        private String name;
    }
}
