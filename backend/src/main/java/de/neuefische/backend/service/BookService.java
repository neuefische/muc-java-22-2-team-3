package de.neuefische.backend.service;

import de.neuefische.backend.model.Book;
import de.neuefische.backend.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private BookRepository bookRepository;



    public BookService(BookRepository bookRepo){
        this.bookRepository = bookRepo;
    }

    public List<Book> getBookList(){
        return bookRepository.getBookList();
    }

    public Book addBookToList(Book newBook){
        IDGenerator idGenerator = new IDGenerator();
        String id = idGenerator.generateID();
        Book book1 = new Book(id, newBook.getTitle(), newBook.getAuthor(), newBook.getIsbn());
        return bookRepository.addBookToList(book1);

    }


}
