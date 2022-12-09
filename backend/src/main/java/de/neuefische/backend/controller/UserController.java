package de.neuefische.backend.controller;
import de.neuefische.backend.model.Book;
import de.neuefische.backend.model.BookUser;
import de.neuefische.backend.model.BookUserDTO;

import de.neuefische.backend.model.FavoriteBook;
import de.neuefische.backend.model.Status;
import de.neuefische.backend.service.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping()
    public List<BookUser> getAllUser(){
       return userService.getAllUser();
    }

    @PostMapping("/signup/")
    public BookUser addUser(@RequestBody BookUserDTO bookUserDTO){
        return userService.addUser(bookUserDTO);
    }

    @GetMapping("me")
    public String helloMe(Principal principal){

        if(principal != null){
            return principal.getName();
        }
        return "anonymousUser";
    }

    @GetMapping("/me/favoritebooks/")
    public List<Book> getFavoriteBooks(Principal principal){
        return userService.getFavoriteBookList(principal.getName());
    }

    @GetMapping("/me/favoritebooks/search/")
    public List<Book> getBook(Principal principal,
            @RequestParam(name="author", required=false) String author,
            @RequestParam(name="title", required=false) String keyword,
            @RequestParam(name="isbn", required=false) String isbn){
        return userService.getBookBy(principal.getName(), author, keyword, isbn);
    }

    @PutMapping("/me/favoritebooks/{bookId}")
    public Set<FavoriteBook> addBookToFavorites(Principal principal, @PathVariable String bookId){
        return userService.addBookToFavorits(principal.getName(),bookId);
    }

    @PutMapping("/me/favoritebooks/update/{bookId}")
    public Set<FavoriteBook> updateBookStatus(Principal principal, @PathVariable String bookId){
        return userService.updateBookStatus(principal.getName(), bookId);
    }

    @DeleteMapping("/me/favoritebooks/{bookId}")
    public Set<FavoriteBook> deleteBookFromFavorites(Principal principal, @PathVariable String bookId){
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

    @PostMapping("logout")
    public String logout(HttpSession httpSession){
        httpSession.invalidate();
        SecurityContextHolder.clearContext();
        return "anonymousUser";
    }

}
