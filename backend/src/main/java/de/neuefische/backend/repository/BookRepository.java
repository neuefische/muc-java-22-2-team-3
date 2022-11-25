package de.neuefische.backend.repository;

import de.neuefische.backend.model.Book;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class BookRepository {

    private List<Book> bookList;

    public BookRepository(){
        this.bookList = new ArrayList<>();
    }

    public List<Book> getBookList() {
        return bookList;
    }

    public Book addBookToList(Book newBook){
        this.bookList.add(newBook);
        return newBook;
    }

    public boolean deleteBook(String id) {
        for (Book book: bookList) {
            if(book.getId().equals(id)){
                return bookList.remove(book);
            }

        }
        return false;
    }
    public Book getBookByID(String id){
        for(Book book: bookList){
            if(book.getId().equals(id)){
                return book;
            }
        }
        return null;
    }



}
