package de.neuefische.backend.controller;

import de.neuefische.backend.model.BookUser;
import de.neuefische.backend.model.FavoriteBook;
import de.neuefische.backend.model.BookUser;
import de.neuefische.backend.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.HashSet;
import java.util.Set;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    UserRepository userRepository;



    @Test
    void test_getAllUser() throws Exception {

        Set<String> newSet = new HashSet<>();
        newSet.add("123");
        BookUser newUser = new BookUser("1","AntonM", "password", "Anton" , "Mustermann",newSet);

        userRepository.save(newUser);

        ResultActions result = mockMvc.perform(get("/books/users"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())

                .andExpect(content().json("""
                        [{"id":"1",
                        "username":"AntonM",
                        "password":"password",
                        "firstname":"Anton",
                        "lastname":"Mustermann",
                        "favoriteBookSet":["123"]
                        }]
                        """));
    }

    @Test
    void test_getFavoriteBooks() {
    }

    @Test
    void test_addBookToFavorites() {
    }

    @Test
    void test_deleteBookFromFavorites() {
    }


}