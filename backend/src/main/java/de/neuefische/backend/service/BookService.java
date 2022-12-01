package de.neuefische.backend.service;

import de.neuefische.backend.model.Book;
import de.neuefische.backend.model.BookDTO;
import de.neuefische.backend.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final IDGenerator idGenerator;



    public BookService(IDGenerator idGenerator, BookRepository bookRepo){
        this.idGenerator = idGenerator;
        this.bookRepository = bookRepo;
    }

    public List<Book> getBookList(){
        return bookRepository.findAll();
    }

    public Book addBookToList(BookDTO newBook){
        String id = idGenerator.generateID();
        Book book1 = new Book(id, newBook.getTitle(), newBook.getAuthor(), newBook.getIsbn());
        return bookRepository.insert(book1);
    }
    public boolean deleteBook(String id){
        if(bookRepository.existsById(id)){
            bookRepository.deleteById(id);
            return true;
        }
        return false;
    }


    public List<Book> getBookBy(String id, String name, String keyword, String isbn){
        List<Book> newList = new ArrayList<>();
        newList.add(bookRepository.findById(id).orElseThrow(NoSuchElementException::new));

        if(name != null){
            newList = bookRepository.findBooksByAuthor(name);
        }

        if(isbn != null){
            newList.add(bookRepository.findBookByIsbn(isbn));
        }

        if(keyword != null){
            newList = bookRepository.findBooksByTitle(keyword);
        }

        return newList;
    }



    public Book getBookByID(String id){
        return bookRepository.findById(id).orElseThrow(NoSuchElementException:: new);
    }

    public List<Book> getBookByKeyword(String keyword){
        return bookRepository.findBooksByTitle(keyword);
    }

    public Book getBookByISBN(String isbn){
        return bookRepository.findBookByIsbn(isbn);
    }

    public List<Book> getBookByAuthor(String name){
        return bookRepository.findBooksByAuthor(name);
    }

}
