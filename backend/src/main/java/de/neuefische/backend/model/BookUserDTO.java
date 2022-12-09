package de.neuefische.backend.model;

import java.util.Set;

public record BookUserDTO(String username,
                          String password,
                          String firstname,
                          String lastname,
                          Set<FavoriteBook> favoriteBookSet) {
}
