package de.neuefische.backend.model;

import lombok.Data;

@Data
public class FavoriteBook extends Book{
    Status status;

    public FavoriteBook(Book book){
        this.setId(book.getId());
        this.setAuthor(book.getAuthor());
        this.setIsbn(book.getIsbn());
        this.setPages(book.getPages());
        this.setDescription(book.getDescription());
        this.setPublisher(book.getPublisher());
        this.setSubtitle(book.getSubtitle());
        this.setTitle(book.getTitle());
        this.setWebsite(book.getWebsite());
        this.status = Status.TOREAD;
    }
}
