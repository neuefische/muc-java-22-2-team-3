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
}