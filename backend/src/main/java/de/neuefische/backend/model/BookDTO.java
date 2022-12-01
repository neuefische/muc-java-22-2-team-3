package de.neuefische.backend.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data

@NoArgsConstructor
public class BookDTO {
    private String isbn;
    private String title;
    private String author;
}
