package de.neuefische.backend.controller;

import de.neuefische.backend.model.Book;

import de.neuefische.backend.model.BookDTO;
import de.neuefische.backend.service.BookService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("books")
public class BookController {
    private final BookService bookService;


    public BookController(BookService bookService){
        this.bookService = bookService;
    }

    @GetMapping
    public List<Book> getAllBooks(){
        return bookService.getBookList();
    }
    @GetMapping("/search/")
    public List<Book> getBook(
                              @RequestParam(name="author", required=false) String author,
                              @RequestParam(name="title", required=false) String keyword,
                              @RequestParam(name="isbn", required=false) String isbn){
        return bookService.getBookBy(author, keyword, isbn);
    }

    @GetMapping("/{id}")
    public Book getBookByID(@PathVariable String id){
        return bookService.getBookByID(id);
    }

    @PostMapping
    public Book addToBookList(@RequestBody BookDTO newBook){
        return bookService.addBookToList(newBook);
    }

    @DeleteMapping("/{id}")
    public boolean deleteBook(@RequestBody @PathVariable String id){
        return bookService.deleteBook(id);
    }

}
