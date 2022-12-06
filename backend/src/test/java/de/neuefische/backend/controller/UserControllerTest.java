package de.neuefische.backend.controller;

import de.neuefische.backend.model.BookUser;
import de.neuefische.backend.model.FavoriteBook;
import de.neuefische.backend.model.BookUser;
import de.neuefische.backend.repository.UserRepository;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.security.Principal;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.databind.ObjectMapper;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class UserControllerTest {


    @Autowired
    MockMvc mockMvc;

    @Autowired
    UserRepository userRepository;

    @Mock
    Principal principal;

    @WithMockUser("spring")
    @Test
    @DirtiesContext
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
    @DirtiesContext
    void addUser() throws Exception {


        mockMvc.perform(post("/users")
                        .param("username" , "username")
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
    @WithMockUser("spring")
    @Test
    void getFavoriteBooks() {
    }
//    @WithMockUser("spring")
//    @Test
//    void addBookToFavorites() throws Exception {
//        ResultActions actual = mockMvc.perform(post("/users")
//                        .param("username", "username")
//                        .param("firstname", "firstname")
//                        .param("lastname", "lastname")
//                        .param("password", "password")
//                );
//                mockMvc.perform(put("/me/favoritebooks/123")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content("""
//                                "username": "username",
//                                "firstname": "firstname",
//                                "lastname": "lastname",
//                                "password": "password",
//                                "favoriteBookSet" : [123]
//                                """
//                        ))
//                .andExpect(status().isOk());
//
//
//    }
//    @WithMockUser("spring")
//    @Test
//    void deleteBookFromFavorites() {
//    }
//    @WithMockUser("spring")
//    @Test
//    void deleteUser() {
//    }
//    @WithMockUser("spring")
//    @Test
//    void login() {
//    }
//    @WithMockUser("spring")
//    @Test
//    void logout() {
//    }
}