package de.neuefische.backend.service;

import de.neuefische.backend.model.BookUser;

import de.neuefische.backend.repository.UserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserService implements UserDetailsService {


    private final UserRepository userRepository;
    private IDGenerator idGenerator= new IDGenerator();

    public UserService(UserRepository userRepo){
        this.userRepository = userRepo;
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

    public BookUser addUser(String username, String firstname, String lastname, String password){
        String userId = idGenerator.generateID();
        BookUser newUser = new BookUser(userId, username, password, firstname,  lastname, new HashSet<>());
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

    public Set<String> getFavoriteBookList(String username) {
       return userRepository.findByUsername(username).orElseThrow().favoriteBookSet();
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
