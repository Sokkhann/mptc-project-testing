package mptc.co.movie_rating.freature.item;

import org.springframework.stereotype.Service;

@Service
public interface ItemService {
    Object getMovieDetails(String movieId);

    // Search movies by title
    Object searchMovies(String query);
}