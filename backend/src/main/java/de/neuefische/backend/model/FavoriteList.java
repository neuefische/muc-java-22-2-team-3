package de.neuefische.backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class FavoriteList {

    private String favoriteId;
    private Set<FavoriteBook> favoriteBookList;

}
