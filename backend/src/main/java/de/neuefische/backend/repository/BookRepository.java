package de.neuefische.backend.repository;

import de.neuefische.backend.model.Book;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface BookRepository extends MongoRepository<Book, String> {




}
