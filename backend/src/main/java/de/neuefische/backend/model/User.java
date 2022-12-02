package de.neuefische.backend.model;

public record User(
        String id,
        String firstname,
        String lastname,

        FavoriteList favoriteListe

) {
}

