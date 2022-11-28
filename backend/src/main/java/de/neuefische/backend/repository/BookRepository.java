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

    public List<Book> getBookByKeyword(String keyword){
        List<Book> findedBooks = new ArrayList<>();
        for(Book book: bookList){
            if(book.getTitle().matches(keyword)){
                findedBooks.add(book);
            }
        }
        return findedBooks;
    }

    public Book getBookByISBN(String isbn){
        for(Book book: bookList){
            if(book.getIsbn().equals(isbn)){
                return book;
            }
        }
        return null;
    }

    public List<Book> getBookByAuthor(String name){
        List<Book> newList = new ArrayList<>();
        for(Book book: bookList){
            if(book.getAuthor().toLowerCase().matches(name.toLowerCase())){
                newList.add(book);
            }
        }
        return newList;
    }




}
