package de.neuefische.backend.service;

import de.neuefische.backend.model.Book;
import de.neuefische.backend.model.BookUser;
import de.neuefische.backend.repository.BooksRepository;
import de.neuefische.backend.model.BookUserDTO;
import de.neuefische.backend.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.test.annotation.DirtiesContext;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    UserRepository userRepository= mock(UserRepository.class);
    BooksRepository booksRepository=mock(BooksRepository.class);
    UserService userService = new UserService(userRepository, booksRepository);

@DirtiesContext
    @Test
    void test_getAllUser() {

        List<BookUser> userList = new ArrayList<>();

        when(userRepository.findAll()).thenReturn(userList);
        List<BookUser> result = userService.getAllUser();
        assertEquals(result, userList);

    }
    @DirtiesContext
    @Test
    void test_addUser() {

        BookUserDTO user = new BookUserDTO("username","password","Max", "Mustermann", new HashSet<>());
        BookUser bookUser = new BookUser("123", user.username(), user.password(),
                user.firstname(), user.lastname(), user.favoriteBookSet());
        when(userRepository.save(bookUser)).thenReturn(bookUser);
        BookUser result = userService.addUser(user);

        assertEquals(result.username(), user.username());
    }
    @DirtiesContext
    @Test
    void test_deleteUser() {
        List<BookUser> newList = new ArrayList<>();
        BookUser newUser = new BookUser("123","username","password","Max", "Mustermann", new HashSet<>());
        userRepository.save(newUser);

        doNothing().when(userRepository);

        List<BookUser> result = userService.deleteUser("123");

        assertEquals(result, newList);
    }
    @DirtiesContext
    @Test
    void test_addBookToFavorits() {
        String bookId = (new IDGenerator()).generateID();

        BookUser user = new BookUser("123","username","password","Max", "Mustermann", new HashSet<>());

        Set<String> newList = new HashSet<>();
        newList.add(bookId);

        when(userRepository.findByUsername("username")).thenReturn(Optional.of(user));

        Set<String> result = userService.addBookToFavorits("username",bookId);

        assertEquals(result, newList);
    }
    @DirtiesContext
    @Test
    void test_getFavoriteBookList() {
        BookUser user = new BookUser("123","username","password","Max", "Mustermann", new HashSet<>());
        userRepository.save(user);

        List<Book> newList = new ArrayList<>();

        when(userRepository.findByUsername("username")).thenReturn(Optional.of(user));
        List<Book> result = userService.getFavoriteBookList("username");

        assertEquals(result, newList);
    }
    @DirtiesContext
    @Test
    void test_deleteBookFromFavorites() {
        String bookId = (new IDGenerator()).generateID();

        BookUser user = new BookUser("123","username","password","Max", "Mustermann",new HashSet<>(Set.of(bookId)));

        Set<String> newList = new HashSet<>();

        when(userRepository.findByUsername("username")).thenReturn(Optional.of(user));

        Set<String> result = userService.deleteBookFromFavorites("username",bookId);

        assertEquals(result, newList);

    }
    @DirtiesContext
    @Test
    void test_getUserId() {
        BookUser user = new BookUser("123","username","password","Max", "Mustermann", new HashSet<>());
        userRepository.save(user);

        when(userRepository.findByUsername("username")).thenReturn(Optional.of(user));

        String result  = userService.getUserId("username");

        assertEquals("123", result);

    }
}