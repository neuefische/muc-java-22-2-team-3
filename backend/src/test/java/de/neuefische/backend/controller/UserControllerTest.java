package de.neuefische.backend.controller;

import de.neuefische.backend.model.BookUser;
import de.neuefische.backend.model.FavoriteBook;
import de.neuefische.backend.model.BookUser;
import de.neuefische.backend.repository.UserRepository;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.HashSet;
import java.util.Set;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class UserControllerTest {


    @Autowired
    MockMvc mockMvc;

    @Autowired
    UserRepository userRepository;


    @WithMockUser("spring")
    @Test
    void getAllUser() throws Exception {
        BookUser user1 = new BookUser( "123", "username", "password",  "firstname",
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
    @WithMockUser("spring")
    @Test
    void addUser() {
    }
    @WithMockUser("spring")
    @Test
    void getFavoriteBooks() {
    }
    @WithMockUser("spring")
    @Test
    void addBookToFavorites() {
    }
    @WithMockUser("spring")
    @Test
    void deleteBookFromFavorites() {
    }
    @WithMockUser("spring")
    @Test
    void deleteUser() {
    }
    @WithMockUser("spring")
    @Test
    void login() {
    }
    @WithMockUser("spring")
    @Test
    void logout() {
    }
}