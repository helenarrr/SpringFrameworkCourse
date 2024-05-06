package tech.helen_app.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import tech.helen_app.dao.BookRepository;
import tech.helen_app.entity.Book;
import tech.helen_app.service.BookService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
class BookControllerTest {
    @Mock
    BookService bookService;

    @InjectMocks
    BookController bookController;

    @Test
    void getBook() {

        var listOfBooks = List.of(
                new Book("книга1"),
                new Book("книга2"),
                new Book("книга3"),
                new Book("книга4"),
                new Book("книга5")
        );

        doReturn(listOfBooks).when(this.bookService).findAll();

        var responseEntity = this.bookController.getAllBooks();

        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(listOfBooks, responseEntity.getBody());
    }
}