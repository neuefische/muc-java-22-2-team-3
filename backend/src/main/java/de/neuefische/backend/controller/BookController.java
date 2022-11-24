package de.neuefische.backend.controller;

import de.neuefische.backend.model.Book;
import de.neuefische.backend.service.BookService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("books")
public class BookController {
    private BookService bookService;

    public BookController(BookService bookService){
        this.bookService = bookService;
    }

    @GetMapping
    public List<Book> getAllBooks(){
        return bookService.getBookList();
    }

    @PostMapping
    public Book addToBookList(@RequestBody Book newBook){
        return bookService.addBookToList(newBook);
    }

}
