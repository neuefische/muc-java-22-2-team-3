package de.neuefische.backend.service;

import de.neuefische.backend.model.FavoriteBook;
import de.neuefische.backend.model.User;
import de.neuefische.backend.repository.FavoriteBooksRepository;
import de.neuefische.backend.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavoriteBooksService {
    private  FavoriteBooksRepository favoriteBooksRepository;

    public FavoriteBooksService(FavoriteBooksRepository favoriteBooksRepository) {
        this.favoriteBooksRepository = favoriteBooksRepository;
    }

    public FavoriteBook update(FavoriteBook favoriteBook) {
        return favoriteBooksRepository.update(favoriteBook);
    }

}