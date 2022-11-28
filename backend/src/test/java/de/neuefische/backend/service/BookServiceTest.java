package de.neuefische.backend.service;
import de.neuefische.backend.model.Book;
import de.neuefische.backend.repository.BookRepository;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BookServiceTest {

    BookRepository bookRepository = mock(BookRepository.class);
    IDGenerator idGenerator = mock(IDGenerator.class);
    BookService bookService = new BookService(idGenerator, bookRepository);

    @Test
    void test_getBookList_whenListEmpty() {
        List<Book> emptyList = new ArrayList<>();
        when(bookRepository.getBookList()).thenReturn(emptyList);

        List<Book> result = bookService.getBookList();

        assertEquals(emptyList, result);
    }

    @Test
    void test_addBookToList() {

        Book newBook = new Book();
        when(bookRepository.addBookToList(newBook)).thenReturn(newBook);

        Book result = bookService.addBookToList(newBook);

        assertEquals(newBook, result);
    }

    @Test
    void test_getBookByID(){
        String id = "123";

        Book newBook = new Book(id, "","","");

        when(bookRepository.getBookByID(id)).thenReturn(newBook);

        Book result = bookService.getBookByID(id);

        assertEquals(newBook, result);
    }

    @Test
    void test_deleteBook() {
        String id = "123";

        Book newBook = new Book(id, "","","");
        bookRepository.addBookToList(newBook);

        when(bookRepository.deleteBook(id)).thenReturn(true);

        boolean result = bookService.deleteBook(id);

        assertTrue(result);

    }

    @Test
    void test_getBookByKeyword(){
        String keyword = "a";

        Book newBook = new Book("id", "aaaaaa","","");
        List<Book> newList = new ArrayList<>();
        newList.add(newBook);

        when(bookRepository.getBookByKeyword(keyword)).thenReturn(newList);

        List<Book> result = bookService.getBookByKeyword(keyword);

        assertEquals(newList, result);
    }

    @Test
    void test_getBookByISBN(){
        String isbn= "isbn";

        Book newBook = new Book("id", "aaaaaa","","isbn");
        bookService.addBookToList(newBook);

        when(bookRepository.getBookByISBN(isbn)).thenReturn(newBook);

        Book result = bookService.getBookByISBN(isbn);

        assertEquals(newBook, result);
    }
    @Test
    void test_getBookByAuthor(){
        String name = "me";

        Book newBook = new Book("id", "aaaaaa","me","isbn");
        List<Book> newList = new ArrayList<>();
        newList.add(newBook);

        when(bookRepository.getBookByAuthor(name)).thenReturn(newList);

        List<Book> result = bookService.getBookByAuthor(name);

        assertEquals(newList, result);
    }


}