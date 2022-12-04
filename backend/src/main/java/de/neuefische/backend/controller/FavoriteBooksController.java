package de.neuefische.backend.controller;

import de.neuefische.backend.model.FavoriteBook;
import de.neuefische.backend.service.FavoriteBooksService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books/favoritebooks")
public class FavoriteBooksController {

    private FavoriteBooksService favoriteBooksService;

    public FavoriteBooksController(FavoriteBooksService favoriteBooksService) {
        this.favoriteBooksService = favoriteBooksService;
    }
    @GetMapping()
    public List<FavoriteBook>  getFavoriteBookList(){
        return favoriteBooksService.getFavoriteBookList();
    }

    @PostMapping
    public FavoriteBook addFavoriteBook(@RequestBody String bookId) {
        return favoriteBooksService.addToFavoriteBooks(bookId);
    }

    @PutMapping
    public String updateFavoriteBookList(){
        return "update ok";
    }

    @DeleteMapping()
        public String deleteFavoriteBook(){
        return "delete ok";
    }



}
