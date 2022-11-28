package de.neuefische.backend.controller;

import de.neuefische.backend.model.Book;
import de.neuefische.backend.repository.BookRepository;
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
    BookRepository bookRepository;

@Autowired
ObjectMapper objectMapper;
    @DirtiesContext
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

    @DirtiesContext
    @Test
    void test_addToBookList() throws Exception{
        Book newBook = new Book("123", "me", "Java" , "isbn");

        bookRepository.addBookToList(newBook);

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

    Book book = new Book ("1","me", "me","isbn");
    bookRepository.addBookToList(book);
    String id = book.getId();
    bookRepository.deleteBook(id);
    mockMvc.perform(delete("http://localhost:8080/books/"+id)

                    .contentType(MediaType.APPLICATION_JSON)
                    .content("""
                        {
                        }"""))
            .andExpect(status().isOk());


    }

    @DirtiesContext
    @Test
    void test_getBookByID() throws Exception{
        String id = "123";
        Book newBook = new Book(id, "me", "Java" , "isbn");

        bookRepository.addBookToList(newBook);

        mockMvc.perform(get("/books/" + id))
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

    @DirtiesContext
    @Test
    void test_getBookByISBN() throws Exception{
        String isbn = "isbn";
        Book newBook = new Book("123", "me", "Java" , "isbn");

        bookRepository.addBookToList(newBook);

        mockMvc.perform(get("/books/by-isbn/?isbn=" + isbn))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                    {
                    "id" : "123",
                        "title": "me",
                        "author": "Java",
                        "isbn": "isbn"
                    }
                    """));
    }
    @DirtiesContext
    @Test
    void test_getBookByKeyword() throws Exception{
        String keyword = "me";
        Book newBook = new Book("123", "me", "Java" , "isbn");

        bookRepository.addBookToList(newBook);

        mockMvc.perform(get("/books/by-keyword/?keyword=" + keyword))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                    [{
                    "id" : "123",
                        "title": "me",
                        "author": "Java",
                        "isbn": "isbn"
                    }]
                    """));
    }

    @DirtiesContext
    @Test
    void test_getBookByAuthor() throws Exception{
        String name = "me";
        Book newBook = new Book("123", "Java", "me" , "isbn");

        bookRepository.addBookToList(newBook);

        mockMvc.perform(get("/books/by-author/?name=" + name))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                    [{
                    "id" : "123",
                        "title": "Java",
                        "author": "me",
                        "isbn": "isbn"
                    }]
                    """));
    }



}