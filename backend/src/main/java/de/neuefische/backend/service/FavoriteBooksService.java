package de.neuefische.backend.service;

import de.neuefische.backend.model.Book;
import de.neuefische.backend.model.FavoriteBook;
import de.neuefische.backend.model.Status;
import de.neuefische.backend.repository.FavoriteBooksRepository;

public class FavoriteBooksService {

    private final FavoriteBooksRepository favoriteBooksRepo;
    private String id;

    public FavoriteBooksService(FavoriteBooksRepository booksRepo) {
        this.favoriteBooksRepo = booksRepo;
        this.id =(new IDGenerator()).generateID();
    }

    public FavoriteBook addToFavoriteBooks(Book book) {
        FavoriteBook newFavoriteBook = new FavoriteBook(book);
        return favoriteBooksRepo.save(newFavoriteBook);
    }

}