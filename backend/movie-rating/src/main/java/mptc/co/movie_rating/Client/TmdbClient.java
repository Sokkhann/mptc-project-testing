package mptc.co.movie_rating.Client;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import mptc.co.movie_rating.freature.item.dto.MovieDetailsResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class TmdbClient {
    private final RestTemplate restTemplate;

    @Value("${tmdb.api.key}")
    private String apiKey;

    @Value("${tmdb.api.base-url}")
    private String baseUrl;

    public TmdbClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    // Fetch movie details by ID and map to DTO
    public MovieDetailsResponse getMovieDetails(String movieId) {
        String url = baseUrl + "/movie/" + movieId + "?api_key=" + apiKey;
        Object rawResponse = restTemplate.getForObject(url, Object.class);

        // Convert raw response to DTO
        return mapToMovieDetailsResponse(rawResponse);
    }

    // Search movies by title
    public Object searchMovies(String query) {
        String url = baseUrl + "/search/movie?api_key=" + apiKey + "&query=" + query;
        return restTemplate.getForObject(url, Object.class);
    }

    // Helper method to map raw response to DTO
    private MovieDetailsResponse mapToMovieDetailsResponse(Object rawResponse) {
        // Use a JSON library like Jackson to parse the raw response
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonResponse = objectMapper.valueToTree(rawResponse);

        MovieDetailsResponse dto = new MovieDetailsResponse();
        dto.setId(jsonResponse.get("id").asInt());
        dto.setHomepage(jsonResponse.get("homepage").asText());
        dto.setOriginalTitle(jsonResponse.get("original_title").asText());
        dto.setOverview(jsonResponse.get("overview").asText());
        dto.setPopularity(jsonResponse.get("popularity").asDouble());
        dto.setPosterPath(jsonResponse.get("poster_path").asText());

        // Map genres
        List<MovieDetailsResponse.Genre> genres = objectMapper.convertValue(
                jsonResponse.get("genres"),
                new TypeReference<List<MovieDetailsResponse.Genre>>() {}
        );
        dto.setGenres(genres);
        return dto;
    }
}
