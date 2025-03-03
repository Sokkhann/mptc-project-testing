package mptc.co.movie_rating.freature.favorite;


import mptc.co.movie_rating.domain.Favorite;
import mptc.co.movie_rating.freature.favorite.dto.FavoriteRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/favorites")
public class FavoriteController {

    private final FavoriteService favoriteService;

    public FavoriteController(FavoriteService favoriteService) {
        this.favoriteService = favoriteService;
    }

    @GetMapping("/check/{anonymousUserId}/{itemId}")
    public ResponseEntity<Map<String, Boolean>> checkFavorite(
            @PathVariable String anonymousUserId,
            @PathVariable String itemId) {
        boolean isFavorite = favoriteService.isFavorite(anonymousUserId, itemId);
        return ResponseEntity.ok(Map.of("isFavorite", isFavorite));
    }

    @PostMapping("/{anonymousUserId}/{itemId}")
    public ResponseEntity<Void> addFavorite(
            @PathVariable String anonymousUserId,
            @PathVariable String itemId) {
        favoriteService.addFavorite(anonymousUserId, itemId);
        return ResponseEntity.status(201).build(); // 201 Created
    }

    @DeleteMapping("/{anonymousUserId}/{itemId}")
    public ResponseEntity<Void> removeFavorite(
            @PathVariable String anonymousUserId,
            @PathVariable String itemId) {
        favoriteService.removeFavorite(anonymousUserId, itemId);
        return ResponseEntity.noContent().build(); // 204 No Content
    }

    @GetMapping("/{anonymousUserId}")
    public ResponseEntity<List<Favorite>> getAllFavoritesByUser(@PathVariable String anonymousUserId) {
        List<Favorite> favorites = favoriteService.getAllFavoritesByUser(anonymousUserId);
        return ResponseEntity.ok(favorites);
    }
}