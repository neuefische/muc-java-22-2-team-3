package de.neuefische.backend.controller;

import de.neuefische.backend.model.Book;
import de.neuefische.backend.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class BookControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    BookRepository bookRepository;


    @Test
    void test_getAllBooks() throws Exception{
        Book newBook = new Book("123", "me", "Java" , "isbn");

        bookRepository.addBookToList(newBook);

        mockMvc.perform(get("/books"))
                .andExpect(status().isOk())
                .andExpect(content().json("""
[{
"id": "123",
"title": "me",
"author": "Java",
"isbn": "isbn"
}]
"""));
    }

    @Test
    void test_addToBookList() throws Exception{
        Book newBook = new Book("123", "me", "Java" , "isbn");

        bookRepository.addBookToList(newBook);

        mockMvc.perform(post("/books").contentType(MediaType.APPLICATION_JSON).content("""
{
"id": "123",
"title": "me",
"author": "Java",
"isbn": "isbn"
}
"""))
                .andExpect(status().isOk())
                .andExpect(content().json("""
{
"id": "123",
"title": "me",
"author": "Java",
"isbn": "isbn"
}
"""));
    }
}