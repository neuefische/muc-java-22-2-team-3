package de.neuefische.backend.controller;

import de.neuefische.backend.model.BookUser;

import de.neuefische.backend.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("books/users")
public class UserController {

    private UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping()
    public List<BookUser> getAllUser(){
       return userService.getAllUser();
    }

    @PostMapping()
    public BookUser addUser(@RequestBody String username, String firstname, String lastname, String password){
        return userService.addUser(username, firstname, lastname, password);
    }

    @GetMapping("me")
    public String helloMe(Principal principal){
        return "Hallo " + principal.getName();
    }

    @GetMapping("/me/favoritebooks/")
    public List<String> getFavoriteBooks(Principal principal){
        return userService.getFavoriteBookList(principal.getName());
    }

    @PutMapping("/me/favoritebooks/{bookId}")
    public boolean addBookToFavorites(Principal principal, @PathVariable String bookId){
        return userService.addBookToFavorits(principal.getName(),bookId);
    }

    @DeleteMapping("/me/favoritebooks/{bookId}")
    public boolean deleteBookFromFavorites(Principal principal, @PathVariable String bookId){
        return userService.deleteBookFromFavorites(principal.getName(),bookId);
    }

    @DeleteMapping("{userId}")
    public boolean deleteUser(@PathVariable String userId){
        return userService.deleteUser(userId);
    }

}
