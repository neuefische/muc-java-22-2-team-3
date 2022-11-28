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

    @DeleteMapping("/{id}")
    public boolean deleteBook(@RequestBody @PathVariable String id){
        return bookService.deleteBook(id);
    }

    @GetMapping("/{id}")
    public Book getBookByID(@PathVariable String id){
        return bookService.getBookByID(id);
    }

    @GetMapping("/by_keyword/")
    public List<Book> getBookByKeyword(@RequestParam(value = "keyword") String keyword){
        return  bookService.getBookByKeyword(keyword);
    }

    @GetMapping("/by_isbn/")
    public Book getBookByISBN(@RequestParam(value="isbn") String isbn){
        return  bookService.getBookByISBN(isbn);
    }

}
