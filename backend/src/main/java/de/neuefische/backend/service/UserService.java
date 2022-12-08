package de.neuefische.backend.service;

import de.neuefische.backend.model.Book;
import de.neuefische.backend.model.BookUser;

import de.neuefische.backend.model.BookUserDTO;
import de.neuefische.backend.repository.BooksRepository;
import de.neuefische.backend.repository.UserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
        BookUser newUser = new BookUser(userId, bookUser.username(), bookUser.password(),
                bookUser.firstname(), bookUser.lastname(), bookUser.favoriteBookSet());
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

    public Set<String> addBookToFavorits(String username, String bookId){
        BookUser user = userRepository.findByUsername(username).orElseThrow();
        Set<String> booksList = user.favoriteBookSet();

        booksList.add(bookId);
             userRepository.save(user);
             return booksList;
    }

    public List<Book> getFavoriteBookList(String username) {
       Set<String> idList = userRepository.findByUsername(username).orElseThrow().favoriteBookSet();

       List<Book> bookList = new ArrayList<>();

       for(String id: idList){
           Book book = booksRepository.findById(id).orElseThrow();
           bookList.add(book);
       }
       return bookList;
    }

    public Set<String> deleteBookFromFavorites(String username, String bookId) {

        BookUser user = userRepository.findByUsername(username).orElseThrow();

        Set<String> booksList = user.favoriteBookSet();

        booksList.remove(bookId);

        userRepository.save(user);

        return booksList;
    }

    public String getUserId(String username){
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User with username: " + username + " not found!"))
                .id();
    }
}
