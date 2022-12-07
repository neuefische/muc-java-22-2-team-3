package de.neuefische.backend.service;

import de.neuefische.backend.model.Book;
import de.neuefische.backend.model.BookDTO;
import de.neuefische.backend.repository.BooksRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class BookService {

    private final BooksRepository bookRepository;

    private final IDGenerator idGenerator;


    public BookService(IDGenerator idGenerator, BooksRepository bookRepo) {
        this.idGenerator = idGenerator;
        this.bookRepository = bookRepo;
    }

    public List<Book> getBookList() {
        return bookRepository.findAll();
    }

    public Book addBookToList(BookDTO newBook) {
        String id = idGenerator.generateID();
        Book book1 = new Book(id, newBook.title(), newBook.author(), newBook.isbn());
        return bookRepository.insert(book1);
    }

    public boolean deleteBook(String id) {
        if (bookRepository.existsById(id)) {
            bookRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<Book> getBookBy(String name, String keyword, String isbn) {
        List<Book> newList = new ArrayList<>();
        List<Book> list = bookRepository.findAll();
        if (name != null) {
            for(Book book: list){
                if(book.getAuthor().toLowerCase().contains(name.toLowerCase())){
                    newList.add(book);
                }
            }
        }

        if (isbn != null) {
            newList.add(bookRepository.findBookByIsbn(isbn));
        }

        if (keyword != null) {
            for(Book book: list){
                if(book.getTitle().toLowerCase().contains(keyword.toLowerCase())){
                    newList.add(book);
                }
            }
        }
        return newList;
    }

    public Book getBookByID(String bookId){
        return bookRepository.findById(bookId).orElseThrow(() -> new NoSuchElementException("No such book found"));
    }

}
