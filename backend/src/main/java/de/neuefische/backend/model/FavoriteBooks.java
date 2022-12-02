package de.neuefische.backend.model;

import lombok.Data;

@Data
public class FavoriteBook extends Book{
    Status status;

    public FavoriteBook(Book book){
        super(id, title, author, isbn)
        this.status = Status.TOREAD;
    }
}
