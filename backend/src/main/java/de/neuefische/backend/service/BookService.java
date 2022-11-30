package de.neuefische.backend.service;
import de.neuefische.backend.model.Book;
import de.neuefische.backend.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private BookRepository bookRepository;
    private IDGenerator idGenerator;


@Autowired
    public BookService(IDGenerator idGenerator, BookRepository bookRepo){
        this.idGenerator = idGenerator;
        this.bookRepository = bookRepo;
    }

    public List<Book> getBookList(){
        return bookRepository.findAll();
    }

    public Book addBookToList(Book newBook){
        String id = idGenerator.generateID();
        Book book1 = new Book(id, newBook.getTitle(), newBook.getAuthor(), newBook.getIsbn());
        return bookRepository.save(book1);
    }
    public boolean deleteBook(String id){

       try{
                bookRepository.deleteById(id);
                return true;
            }catch(Exception e){
                e.getMessage();
                return false;

            }

   }




    public Optional<Book> getBookByID(String id){
        return bookRepository.findById(id);
    }


}
