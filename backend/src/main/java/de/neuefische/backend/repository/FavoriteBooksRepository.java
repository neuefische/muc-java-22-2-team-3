package de.neuefische.backend.repository;
import de.neuefische.backend.model.FavoriteBook;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FavoriteBooksRepository extends MongoRepository<FavoriteBook, String> {
}
