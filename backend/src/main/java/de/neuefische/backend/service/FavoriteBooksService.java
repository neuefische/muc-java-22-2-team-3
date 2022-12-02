package de.neuefische.backend.service;

import de.neuefische.backend.model.FavoriteBook;
import de.neuefische.backend.repository.FavoriteBooksRepository;
import org.springframework.stereotype.Service;


@Service
public class FavoriteBooksService {
    private  FavoriteBooksRepository favoriteBooksRepository;

    public FavoriteBooksService(FavoriteBooksRepository favoriteBooksRepository) {
        this.favoriteBooksRepository = favoriteBooksRepository;
    }

    public FavoriteBook updateFavoriteBook(FavoriteBook favoriteBook) {
        return favoriteBooksRepository.save(favoriteBook);
    }

}