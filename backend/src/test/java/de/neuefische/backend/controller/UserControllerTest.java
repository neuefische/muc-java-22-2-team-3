package de.neuefische.backend.controller;

import de.neuefische.backend.model.Book;
import de.neuefische.backend.model.BookUser;

import de.neuefische.backend.model.FavoriteBook;
import de.neuefische.backend.model.Status;
import de.neuefische.backend.repository.BooksRepository;
import de.neuefische.backend.repository.UserRepository;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;


import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.DirtiesContext;

import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;



import java.util.HashSet;
import java.util.Set;





import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ActiveProfiles("username")
class UserControllerTest {


    @Autowired
    MockMvc mockMvc;

    @Autowired
    UserRepository userRepository;

    @Autowired
    BooksRepository booksRepository;

    @WithMockUser("spring")
    @Test
    @DirtiesContext
    void getAllUser() throws Exception {
        BookUser user1 = new BookUser("123", "username", "password", "firstname",
                "lastname", new HashSet<>());
        userRepository.save(user1);
        mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                        [{"id": "123",
                        "username": "username",
                        "password": "password",
                        "firstname": "firstname",
                        "lastname": "lastname",
                        "favoriteBookSet":[]}]
                                                
                        """));

    }

    @WithMockUser(username = "username")
    @Test
    @DirtiesContext
    void addUser() throws Exception {



        mockMvc.perform(post("/users/signup/")
                        .contentType(MediaType.APPLICATION_JSON).content("""
                        {
                        "username": "username",
                        "password": "password",
                        "firstname": "firstname",
                        "lastname": "lastname",
                        "favoriteBookSet":[]}
                        """)

                .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                         {
                         "username": "username",
                         "firstname": "firstname",
                         "lastname": "lastname",
                         "password": "password",
                         "favoriteBookSet" : []
                        }
                         """)
                );

    }

    @WithMockUser(username = "username")
    @Test
    @DirtiesContext
    void getFavoriteBooks_whenListEmpty() throws Exception {
        BookUser user1 = new BookUser("123", "username", "password", "firstname",
                "lastname", new HashSet<>());
        userRepository.save(user1);

        mockMvc.perform(get("/users/me/favoritebooks/"))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                               []
                                """
                        ));
    }
    @WithMockUser(username = "username")
    @Test
    @DirtiesContext
    void test_getFavoriteBooks() throws Exception {
        Book book = new Book("1", "title","author","isbn");
        booksRepository.save(book);
        FavoriteBook newFavBook = new FavoriteBook(Status.TO_READ, book.getId());
        BookUser user1 = new BookUser("123", "username", "password", "firstname",
                "lastname", new HashSet<>(Set.of(newFavBook)));
        userRepository.save(user1);



        mockMvc.perform(get("/users/me/favoritebooks/"))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                               [{
                            "id": "1",
                            "isbn": "isbn",
                            "title": "title",
                            "subtitle":null,
                            "author": "author",
                            "publisher":null,
                            "pages":0,
                            "description":null,
                            "website":null
                        }]
                                """
                ));
    }

    @WithMockUser(username="username")
    @Test
    @DirtiesContext
    void addBookToFavorites() throws Exception{
        BookUser user1 = new BookUser("123", "username", "password", "firstname",
                "lastname", new HashSet<>());
        userRepository.save(user1);
        mockMvc.perform(put("/users/me/favoritebooks/123")
                        .with(csrf()))

                .andExpect(status().isOk())
                .andExpect(content().json("""
                               [{
                               "status" : "TO_READ",
                               "id" : "123"
                               }]
                                """
                ));

    }



    @WithMockUser(username="username")
    @Test
    @DirtiesContext
    void helloMe() throws Exception {
        mockMvc.perform(get("/users/me"))
                .andExpect(status().isOk())
                .andExpect(content().string("username"));
    }

    @Test
    void helloMe_expectAnonymousUser() throws Exception {

        mockMvc.perform(get("/users/me"))
                .andExpect(status().isOk())
                .andExpect(content().string("anonymousUser"));
    }
    @WithMockUser(username="username")
    @Test
    @DirtiesContext
    void deleteBookFromFavorites() throws Exception {
        Set<FavoriteBook> favoriteBookSet = new HashSet<>();
        favoriteBookSet.add(new FavoriteBook(Status.TO_READ,"123"));
        BookUser user1 = new BookUser("123", "username", "password", "firstname",
                "lastname", favoriteBookSet);
        userRepository.save(user1);
        mockMvc.perform(delete("/users/me/favoritebooks/123")
                        .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                               []
                                """
                ));
    }
    @WithMockUser(username="username")
    @Test
    @DirtiesContext
    void deleteUser() throws Exception {
        BookUser user1 = new BookUser("123", "username", "password", "firstname",
                "lastname", new HashSet<>());
        userRepository.save(user1);
        mockMvc.perform(delete("/users/123")
                        .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                               []
                                """
                ));
    }

}