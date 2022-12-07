package de.neuefische.backend.controller;

import de.neuefische.backend.model.BookUser;

import de.neuefische.backend.repository.UserRepository;

import org.junit.jupiter.api.Test;

import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;



import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.DirtiesContext;

import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;


import java.security.Principal;
import java.util.HashSet;
import java.util.Set;


import com.fasterxml.jackson.databind.ObjectMapper;


;
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

    @Mock
    Principal principal;
    @Autowired
    private ObjectMapper objectMapper;

    @WithMockUser("spring")
    @Test
    @DirtiesContext
    void getAllUser() throws Exception {
        BookUser user1 = new BookUser("123", "username", "password", "firstname",
                "lastname", new HashSet());
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

    @WithMockUser()
    @Test
    @DirtiesContext
    void addUser() throws Exception {


        mockMvc.perform(post("/users")
                        .param("username", "username")
                        .param("firstname", "firstname")
                        .param("lastname", "lastname")
                        .param("password", "password")
                )
                .andExpect(status().isOk())
                .andExpect(content().json("""
                         {
                         "username": "username",
                         "firstname": "firstname",
                         "lastname": "lastname",
                         "password": "password",
                         "favoriteBookSet" : []
                        }
                         """));

    }

    @WithMockUser(username = "username")
    @Test
    @DirtiesContext
    void getFavoriteBooks() throws Exception {
        BookUser user1 = new BookUser("123", "username", "password", "firstname",
                "lastname", new HashSet());
        userRepository.save(user1);
        mockMvc.perform(get("/users/me/favoritebooks/"))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                               []
                                """
                        ));
    }

    @WithMockUser(username="username")
    @Test
    @DirtiesContext
    void addBookToFavorites() throws Exception{
        BookUser user1 = new BookUser("123", "username", "password", "firstname",
                "lastname", new HashSet());
        userRepository.save(user1);
        mockMvc.perform(put("/users/me/favoritebooks/123"))

                .andExpect(status().isOk())
                .andExpect(content().json("""
                               ["123"]
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
        BookUser user1 = new BookUser("123", "username", "password", "firstname",
                "lastname", new HashSet(Set.of("123")));
        userRepository.save(user1);
        mockMvc.perform(delete("/users/me/favoritebooks/123"))
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
                "lastname", new HashSet());
        userRepository.save(user1);
        mockMvc.perform(delete("/users/123"))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                               []
                                """
                ));
    }
    @WithMockUser(username="username")
    @Test
    @DirtiesContext
    void login() {
    }
    @WithMockUser(username="username")
    @Test
    @DirtiesContext
    void logout() {
    }
}