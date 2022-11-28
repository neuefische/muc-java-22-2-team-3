package de.neuefische.backend.model;

import de.neuefische.backend.service.IDGenerator;
import lombok.Data;

@Data
public class Book {

    private String id;
    private String title;
    private String author;
    private String isbn;

    public Book(){
        this.id = idGenerator();
    }

    public Book(String title, String author, String isbn){
        this.id = idGenerator();
        this.title = title;
        this.author = author;
        this.isbn = isbn;
    }

    private String idGenerator(){
        IDGenerator idGenerator = new IDGenerator();
        return idGenerator.generateID();
    }

}
