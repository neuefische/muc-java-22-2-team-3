package de.neuefische.backend.controller;

import de.neuefische.backend.model.Book;
import de.neuefische.backend.repository.BookRepository;
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
    BookRepository bookRepository;

@Autowired
ObjectMapper objectMapper;

    @Test
    void test_getAllBooks() throws Exception{
        Book newBook = new Book("me", "Java" , "isbn");

        bookRepository.addBookToList(newBook);

        mockMvc.perform(get("/books"))
                .andExpect(status().isOk())
                .andExpect(content().json("""
[{
"title": "me",
"author": "Java",
"isbn": "isbn"
}]
"""));
    }

    @DirtiesContext
    @Test
    void test_getBookByKeyword() throws Exception{
        String keyword = "me";
        Book newBook = new Book("me", "Java" , "isbn");

        bookRepository.addBookToList(newBook);

        mockMvc.perform(get("/books/by_keyword/").param("keyword",keyword))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                    [{
                        "title": "me",
                        "author": "Java",
                        "isbn": "isbn"
                    }]
                    """));
    }

    @DirtiesContext
    @Test
    void test_addToBookList() throws Exception{
        Book newBook = new Book("me", "Java" , "isbn");

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


    @Test
    void test_getBookByID() throws Exception{

        Book newBook = new Book("me", "Java" , "isbn");
        String id = newBook.getId();
        bookRepository.addBookToList(newBook);

        mockMvc.perform(get("/books/" + id))
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
    void test_getBookByISBN() throws Exception{
        String isbn = "isbn";
        Book newBook = new Book("me", "Java" , "isbn");

        bookRepository.addBookToList(newBook);

        mockMvc.perform(get("/books/by_isbn/").param("isbn",isbn))
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

        Book book = new Book ("me", "me","isbn");
        bookRepository.addBookToList(book);
        String id = book.getId();
        mockMvc.perform(delete("http://localhost:8080/books/"+id))
                .andExpect(content().string("true"))
                .andExpect(status().isOk());
    }


}