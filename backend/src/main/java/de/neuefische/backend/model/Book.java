package de.neuefische.backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {

    private String id;
    private String isbn;
    private String title;
    private String subtitle;
    private String author;
    private String publisher;
    private int pages;
    private String description;
    private String website;

    public Book(String id, String title, String author, String isbn){
        this.id = id;
        this.isbn = isbn;
        this.title = title;
        this.author = author;
    }
}
