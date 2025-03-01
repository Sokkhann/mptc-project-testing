package mptc.co.movie_rating.freature.item;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/items")
@AllArgsConstructor
public class ItemController {

    private ItemService itemService;

    // Fetch movie details by ID
    @GetMapping("movie/{id}")
    public ResponseEntity<Object> getMovieDetails(@PathVariable String id) {
        Object movieDetails = itemService.getMovieDetails(id);
        return ResponseEntity.ok(movieDetails);
    }

    // Search movies by title
    @GetMapping("/search")
    public ResponseEntity<Object> searchMovies(@RequestParam String query) {
        Object searchResults = itemService.searchMovies(query);
        return ResponseEntity.ok(searchResults);
    }
}