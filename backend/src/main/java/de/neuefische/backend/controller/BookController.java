package de.neuefische.backend.controller;

import de.neuefische.backend.model.Book;
import de.neuefische.backend.service.BookService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    @DeleteMapping("/{id}")
    public boolean deleteBook(@RequestBody @PathVariable String id){
        return bookService.deleteBook(id);
    }

    @GetMapping("/{id}")
    public Optional<Book> getBookByID(@PathVariable String id){
        return bookService.getBookByID(id);
    }


}
