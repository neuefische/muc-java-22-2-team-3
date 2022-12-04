package de.neuefische.backend.model;

import java.util.Set;

public record User(
        String id,
        String firstname,
        String lastname,
        Set<FavoriteBook> favoriteBookSet
) {
}

