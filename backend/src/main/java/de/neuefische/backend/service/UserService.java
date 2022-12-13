package de.neuefische.backend.service;

import de.neuefische.backend.model.Book;
import de.neuefische.backend.model.BookUser;

import de.neuefische.backend.model.FavoriteBook;
import de.neuefische.backend.model.Status;
import de.neuefische.backend.model.BookUserDTO;
import de.neuefische.backend.repository.BooksRepository;
import de.neuefische.backend.repository.UserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService implements UserDetailsService {


    private final UserRepository userRepository;
    private IDGenerator idGenerator= new IDGenerator();
    private BooksRepository booksRepository;

    public UserService(UserRepository userRepo, BooksRepository booksRepo){
        this.userRepository = userRepo;
        this.booksRepository = booksRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        BookUser user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User with username: " + username + " not found!"));

        return new User(user.username(), user.password(), List.of());
    }

    public List<BookUser> getAllUser(){
        return userRepository.findAll();
    }

    public BookUser addUser(BookUserDTO bookUser){
        String userId = idGenerator.generateID();
        String password = new Argon2PasswordEncoder().encode(bookUser.password());
        Set<FavoriteBook> newBookList= new HashSet<>();
        BookUser newUser = new BookUser(userId, bookUser.username(), password,
                bookUser.firstname(), bookUser.lastname(), newBookList);
        userRepository.save(newUser);
        return newUser;
    }

    public List<BookUser> deleteUser(String id){
        try {
            userRepository.deleteById(id);
        }catch(Exception e){
            e.getMessage();
        }
        return userRepository.findAll();
    }

    public Set<FavoriteBook> addBookToFavorits(String username, String bookId){
        BookUser user = userRepository.findByUsername(username).orElseThrow();
        Set<FavoriteBook> booksList = user.favoriteBookSet();
        FavoriteBook newFavBook = new FavoriteBook(Status.TO_READ, bookId);

        booksList.add(newFavBook);
        userRepository.save(user);
        return booksList;
    }

    public List<Book> getFavoriteBookList(String username) {
       Set<FavoriteBook> idList = userRepository.findByUsername(username).orElseThrow().favoriteBookSet();

       List<Book> bookList = new ArrayList<>();

       for(FavoriteBook favBook: idList){
           Book book = booksRepository.findById(favBook.getId()).orElseThrow();
           bookList.add(book);
       }
       return bookList;
    }
    public Status getBookStatus(String username, String bookId) {
        Set<FavoriteBook> booksList = userRepository.findByUsername(username).orElseThrow().favoriteBookSet();

        for(FavoriteBook book: booksList){
            if(book.getId().equals(bookId)){
                return book.getStatus();
            }
        }
        return Status.READ;
    }

    public List<Book> deleteBookFromFavorites(String username, String bookId) {

        BookUser user = userRepository.findByUsername(username).orElseThrow();

        Set<FavoriteBook> booksList = user.favoriteBookSet();

        for(FavoriteBook favBook: booksList){
            if(favBook.getId().equals(bookId)){
                booksList.remove(favBook);
            }
        }

        userRepository.save(user);

        return getFavoriteBookList(username);
    }

    public String getUserId(String username){
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User with username: " + username + " not found!"))
                .id();
    }

    public Status updateBookStatus(String username, String bookId){
        BookUser user = userRepository.findByUsername(username).orElseThrow();
        Set<FavoriteBook> booksList = user.favoriteBookSet();
        Status status = Status.READ;
        for(FavoriteBook book: booksList){
            if(book.getId().equals(bookId)){
                status = book.getStatus();
                if(status.equals(Status.TO_READ)){
                    status = Status.READING;
                }
                else{
                    status = Status.READ;
                }
                book.setStatus(status);
            }
        }
        userRepository.save(user);
        return status;
    }

    public List<Book> getBookBy(String username, String name, String keyword, String isbn) {
        List<Book> newList = new ArrayList<>();
        Set<FavoriteBook> favoriteBookSet = userRepository.findByUsername(username).orElseThrow().favoriteBookSet();

        List<Book> list = new ArrayList<>();

        for(FavoriteBook favBook: favoriteBookSet){
            list.add(booksRepository.findById(favBook.getId()).orElseThrow());
        }

        if (name != null) {
            for(Book book: list){
                if(book.getAuthor().toLowerCase().contains(name.toLowerCase())){
                    newList.add(book);
                }
            }
        }

        if (isbn != null) {
            newList.add(booksRepository.findBookByIsbn(isbn));
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

}
