package de.neuefische.backend.controller;

import de.neuefische.backend.model.Book;
import de.neuefische.backend.model.FavoriteBook;
import de.neuefische.backend.repository.BooksRepository;
import de.neuefische.backend.repository.FavoriteBooksRepository;
import de.neuefische.backend.service.BookService;
import de.neuefische.backend.service.FavoriteBooksService;
import de.neuefische.backend.service.IDGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class FavoriteBooksControllerTest {



    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private FavoriteBooksService favoriteBooksService;

    @Autowired
    private BooksRepository booksRepository;

    @Test
    void test_addFavoriteBook() throws Exception {
        String bookId = "123";
        Book newBook = new Book();
        newBook.setId(bookId);

        booksRepository.save(newBook);

        mockMvc.perform(post("/books/favoritebooks/").content(bookId))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                           { 
                           "status": "TO_READ",
                            "book": {
                                "id": "123",
                                "author": "",
                                "isbn": "",
                                "title": "",
                                "description": null,
                                "subtitle": null,
                                "publisher": null,
                                "pages": 0,
                                "website": null
                                }
                            }
                        """));
    }
}
