package de.neuefische.backend.service;

import de.neuefische.backend.model.Book;
import de.neuefische.backend.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private BookRepository bookRepository;
    private IDGenerator idGenerator;



    public BookService(IDGenerator idGenerator, BookRepository bookRepo){
        this.idGenerator = idGenerator;
        this.bookRepository = bookRepo;
    }

    public List<Book> getBookList(){
        return bookRepository.getBookList();
    }

    public Book addBookToList(Book newBook){
        String id = idGenerator.generateID();
        Book book1 = new Book(id, newBook.getTitle(), newBook.getAuthor(), newBook.getIsbn());
        return bookRepository.addBookToList(book1);
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
