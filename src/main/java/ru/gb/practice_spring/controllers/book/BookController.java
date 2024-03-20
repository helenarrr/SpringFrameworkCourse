package ru.gb.practice_spring.controllers.book;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gb.practice_spring.entity.Book;
import ru.gb.practice_spring.repository.BookRepository;
import ru.gb.practice_spring.services.BookService;

import java.util.List;
import java.util.NoSuchElementException;


// GET /book/{id} - получить описание книги
//* DELETE /book/{id} - удалить книгу,
// * POST /book - создать книгу
@Slf4j
@RestController
@RequestMapping(path = "book")
public class BookController {

    @Autowired
    private BookService service;

    @Autowired
    private BookRepository repository;

    @GetMapping
    public List<Book> getAllBooks() {
        return repository.getAllBooks();
    }

    @GetMapping("/{id}")
    public Book getBook(@PathVariable long id) {
        return service.getBook(id);
    }

    @PostMapping
    public ResponseEntity<Book> createBook(@RequestBody BookRequest bookRequest) {

        log.info("Записана новая книга - {}", bookRequest.getName());

        try {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(service.createBook(bookRequest));
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public Book deleteBook(@PathVariable long id) {
        return service.deleteBook(id);
    }
}
