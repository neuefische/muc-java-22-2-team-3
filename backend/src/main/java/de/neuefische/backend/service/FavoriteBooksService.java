package de.neuefische.backend.service;
import de.neuefische.backend.model.FavoriteBook;
import de.neuefische.backend.repository.FavoriteBooksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;


@Service
public class FavoriteBooksService {


    private final FavoriteBooksRepository favoriteBooksRepository;

    private final IDGenerator idGenerator;

    @Autowired
    public FavoriteBooksService(FavoriteBooksRepository favoriteBooksRepository, IDGenerator idGenerator) {
        this.favoriteBooksRepository = favoriteBooksRepository;
        this.idGenerator = idGenerator;
    }

    public List<FavoriteBook> getFavoriteBookList() {
        return favoriteBooksRepository.findAll();
    }

    public FavoriteBook findFavoriteBookById(String id) {
        Optional<FavoriteBook> optionalFavoriteBooks = favoriteBooksRepository.findById(id);
        if (optionalFavoriteBooks.isPresent()) {
            return optionalFavoriteBooks.get();
        }
        throw new IllegalArgumentException("List not found!");
    }

        public void deleteBookFromFavoriteList(String id) {
            FavoriteBook favoriteBooks = findFavoriteBookById(id);
            favoriteBooksRepository.delete(favoriteBooks);
        }
    }

