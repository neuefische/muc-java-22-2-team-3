package de.neuefische.backend.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document("books")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Book {
    @Id
    private String id;
    private String author;
    private String isbn;
    private String title;

    private String description;

    private String subtitle;

    private String publisher;

    private int pages;

    private String website;


    public Book(String id, String title, String author, String isbn) {
        this.id = id;
        this.isbn = isbn;
        this.title = title;
        this.author = author;
    }
}
