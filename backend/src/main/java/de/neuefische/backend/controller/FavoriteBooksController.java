package de.neuefische.backend.controller;

import de.neuefische.backend.model.FavoriteBook;
import de.neuefische.backend.repository.FavoriteBooksRepository;
import de.neuefische.backend.service.FavoriteBooksService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("books/favoritebooks")
public class FavoriteBooksController {

    private final FavoriteBooksService favoriteBooksService;

    public FavoriteBooksController(FavoriteBooksService favoriteBooksService) {
        this.favoriteBooksService = favoriteBooksService;
    }
    @GetMapping()
    public List<FavoriteBook> getFavoriteBookList(){

        return null;
    }

    @PostMapping("")

    public FavoriteBook addFavoriteBook(){
        return null;
    }
    @PutMapping("")
    public FavoriteBook updateFavoriteBookList(){
        return null;
    }
    @DeleteMapping()

        public void deleteFavoriteBook(){

    }

}
