package mptc.co.movie_rating.freature.item;

import lombok.AllArgsConstructor;
import mptc.co.movie_rating.Client.TmdbClient;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ItemServiceImpl implements ItemService {

    private TmdbClient tmdbClient;

    // Fetch movie details by ID
    @Override
    public Object getMovieDetails(String movieId) {
        return tmdbClient.getMovieDetails(movieId);
    }

    // Search movies by title
    @Override
    public Object searchMovies(String query) {
        return tmdbClient.searchMovies(query);
    }
}