package mptc.co.movie_rating.freature.item;

import mptc.co.movie_rating.domain.Item;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ItemRepository extends MongoRepository<Item, String> {
}