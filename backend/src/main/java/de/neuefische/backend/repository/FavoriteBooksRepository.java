package de.neuefische.backend.repository;

import de.neuefische.backend.model.FavoriteBook;
import de.neuefische.backend.model.FavoriteList;
import de.neuefische.backend.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FavoriteBooksRepository extends MongoRepository<FavoriteBook, String> {
}
