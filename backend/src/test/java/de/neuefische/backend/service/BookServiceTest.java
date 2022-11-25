package de.neuefische.backend.service;
import de.neuefische.backend.model.Book;
import de.neuefische.backend.repository.BookRepository;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class BookServiceTest {

    BookRepository bookRepository = mock(BookRepository.class);
    BookService bookService = new BookService(bookRepository);

    @Test
    void test_getBookList_whenListEmpty() {
        List<Book> emptyList = new ArrayList<>();
        when(bookService.getBookList()).thenReturn(emptyList);

        List<Book> result = bookService.getBookList();

        assertEquals(emptyList, result);
    }

    @Test
    void test_addBookToList() {

        Book newBook = new Book();
        when(bookService.addBookToList(newBook)).thenReturn(newBook);

        Book result = bookService.addBookToList(newBook);

        assertEquals(newBook, result);
    }





    @Test
    void test_deleteBook() {


        Book newBook = new Book();

        when(bookRepository.addBookToList(newBook)).thenReturn(newBook);
        newBook.setId("1");
        boolean result = bookService.deleteBook("1");

        assertFalse(result);

    }


}