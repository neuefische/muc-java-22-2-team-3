package de.neuefische.backend.service;
import de.neuefische.backend.model.FavoriteBooks;
import de.neuefische.backend.repository.FavoriteBooksRepository;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class FavoriteBooksService {

    private final FavoriteBooksRepository favouriteBooksRepository;

    private final IDGenerator idGenerator;

    public FavoriteBooksService(FavoriteBooksRepository favouriteBooksRepository, IDGenerator idGenerator) {
        this.favouriteBooksRepository = favouriteBooksRepository;
        this.idGenerator = idGenerator;
    }

    public List<FavoriteBooks> getFavoriteBookList() {
        return favouriteBooksRepository.findAll();
    }
}
