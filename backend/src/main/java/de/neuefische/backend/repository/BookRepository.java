package de.neuefische.backend.repository;

import de.neuefische.backend.model.Book;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends MongoRepository<Book, String> {

    Book findBookByIsbn(String isbn);
    List<Book> findBooksByAuthor(String name);
    List<Book> findBooksByTitle(String keyword);


}
