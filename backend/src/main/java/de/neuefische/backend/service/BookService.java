package de.neuefische.backend.service;

import de.neuefische.backend.model.Book;
import de.neuefische.backend.model.BookDTO;
import de.neuefische.backend.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private BookRepository bookRepository;
    private IDGenerator idGenerator;



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
        if(id != null && bookRepository.findById(id).isPresent()){
                newList.add(bookRepository.findById(id).get());
        }
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
        Optional<Book> ob = bookRepository.findById(id);
        if(ob.isPresent()){
            return ob.get();
        }
        return null;
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
