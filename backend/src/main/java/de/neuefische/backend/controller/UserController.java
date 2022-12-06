package de.neuefische.backend.controller;

import de.neuefische.backend.model.BookUser;

import de.neuefische.backend.service.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Set;

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

        if(principal != null){
            return principal.getName();
        }
        return "anonymousUser";
    }

    @GetMapping("/me/favoritebooks/")
    public Set<String> getFavoriteBooks(Principal principal){
        return userService.getFavoriteBookList(principal.getName());
    }

    @PutMapping("/me/favoritebooks/{bookId}")
    public Set<String> addBookToFavorites(Principal principal, @PathVariable String bookId){
        return userService.addBookToFavorits(principal.getName(),bookId);
    }

    @DeleteMapping("/me/favoritebooks/{bookId}")
    public Set<String> deleteBookFromFavorites(Principal principal, @PathVariable String bookId){
        return userService.deleteBookFromFavorites(principal.getName(),bookId);
    }

    @DeleteMapping("{userId}")
    public List<BookUser> deleteUser(@PathVariable String userId){
        return userService.deleteUser(userId);
    }


    @PostMapping("/login")
    public String login(){
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
