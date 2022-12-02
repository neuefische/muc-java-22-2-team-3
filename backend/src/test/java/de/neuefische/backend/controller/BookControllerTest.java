package de.neuefische.backend.controller;

import de.neuefische.backend.model.Book;
import de.neuefische.backend.repository.BooksRepository;
import de.neuefische.backend.service.IDGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;


@SpringBootTest
@AutoConfigureMockMvc
class BookControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    IDGenerator idGenerator;

    @Autowired
    BooksRepository bookRepository;

    @Autowired
    ObjectMapper objectMapper;

    @DirtiesContext
    @Test
    void test_getAllBooks() throws Exception {
        Book newBook = new Book("123", "me", "Java", "isbn");

        bookRepository.insert(newBook);

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

    @DirtiesContext
    @Test
    void test_addToBookList() throws Exception {
        Book newBook = new Book("123", "me", "Java", "isbn");

        bookRepository.insert(newBook);

        mockMvc.perform(post("/books").contentType(MediaType.APPLICATION_JSON).content("""
                        {
                        "title": "me",
                        "author": "Java",
                        "isbn": "isbn"
                        }
                        """))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                        {
                        "title": "me",
                        "author": "Java",
                        "isbn": "isbn"
                        }
                        """));
    }

    @DirtiesContext
    @Test
    void expectSuccessfulDelete() throws Exception {

        Book book = new Book("1", "me", "me", "isbn");
        bookRepository.insert(book);
        String id = book.getId();
        mockMvc.perform(delete("http://localhost:8080/books/" + id)

                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                }"""))
                .andExpect(status().isOk());


    }

    @DirtiesContext
    @Test
    void test_getBookByID() throws Exception {
        String id = "123";
        Book newBook = new Book();
        newBook.setId(id);


        bookRepository.insert(newBook);

        mockMvc.perform(get("/books/?id=" + id))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                        [{
                            "id": "123",
                            "isbn":null,
                            "title":null,
                            "subtitle":null,
                            "author":null,
                            "publisher":null,
                            "pages":0,
                            "description":null,
                            "website":null
                        }]
                        """));
    }

    @DirtiesContext
    @Test
    void test_getBookByISBN() throws Exception {
        String isbn = "isbn";
        Book newBook = new Book();
        newBook.setIsbn(isbn);
        newBook.setId("123");

        bookRepository.save(newBook);

        mockMvc.perform(get("/books/search/?isbn=" + isbn))
                .andExpect(status().isOk());
/*                .andExpect(content().json("""
                        [{
                            "id": "123",
                            "isbn": "isbn",
                            "title":null,
                            "subtitle":null,
                            "author":null,
                            "publisher":null,
                            "pages":0,
                            "description":null,
                            "website":null
                        }]
                        """));*/
    }

    @DirtiesContext
    @Test
    void test_getBookByKeyword() throws Exception {
        String keyword = "me";
        Book newBook = new Book();
        newBook.setTitle(keyword);
        newBook.setId("123");

        bookRepository.insert(newBook);

        mockMvc.perform(get("/books/search/?title=" + keyword))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                        [{
                            "id": "123",
                            "isbn": null,
                            "title": "me",
                            "subtitle":null,
                            "author":null,
                            "publisher":null,
                            "pages":0,
                            "description":null,
                            "website":null
                        }]
                        """));
    }

    @DirtiesContext
    @Test
    void test_getBookByAuthor() throws Exception {
        String name = "me";
        Book newBook = new Book();
        newBook.setAuthor(name);
        newBook.setId("123");

        bookRepository.insert(newBook);

        mockMvc.perform(get("/books/search/?author=" + name))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                        [{
                            "id": "123",
                            "isbn": null,
                            "title":null,
                            "subtitle":null,
                            "author": "me",
                            "publisher":null,
                            "pages":0,
                            "description":null,
                            "website":null
                        }]
                        """));
    }


}