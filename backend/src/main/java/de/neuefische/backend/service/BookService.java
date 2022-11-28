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
        return bookRepository.addBookToList(newBook);
    }
    public boolean deleteBook(String id){

        return bookRepository.deleteBook(id);
    }

    public Book getBookByID(String id){
        return bookRepository.getBookByID(id);
    }


    public List<Book> getBookByKeyword(String keyword){
        return bookRepository.getBookByKeyword(keyword);
    }

    public Book getBookByISBN(String isbn){
        return bookRepository.getBookByISBN(isbn);
    }


}
