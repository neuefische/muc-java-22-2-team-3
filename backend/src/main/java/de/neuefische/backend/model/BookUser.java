package de.neuefische.backend.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

@Document("BookUser")
public record BookUser(
        @Id
        String id,
        String username,
        String password,
        String firstname,
        String lastname,
        Set<String> favoriteBookSet
) {
}

