package de.neuefische.backend.repository;

import de.neuefische.backend.model.Book;
import de.neuefische.backend.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.HashMap;

import java.util.Map;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
     Map<String, User> users = new HashMap<>();


    static User update(User user) {
        users.put(user.id(),user);
        return user;
    }

}