package de.neuefische.backend.repository;

import de.neuefische.backend.model.BookUser;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<BookUser, String> {
    Optional<BookUser> findByUsername(String username);
}
