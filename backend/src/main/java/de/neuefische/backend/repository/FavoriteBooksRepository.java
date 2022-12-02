package de.neuefische.backend.repository;

import de.neuefische.backend.model.FavoriteBook;
import de.neuefische.backend.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.HashMap;

import java.util.Map;

@Repository
public interface FavoriteBooksRepository extends MongoRepository<FavoriteBook, String> {
    Map<String, FavoriteBook> favoriteList = new HashMap<>();


    public default FavoriteBook update(FavoriteBook favoriteBook) {
        favoriteList.put(String.valueOf(favoriteBook.status),favoriteBook);
        return favoriteBook;
    }

}