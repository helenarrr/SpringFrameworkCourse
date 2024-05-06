package tech.helen_app.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.helen_app.entity.Author;
import tech.helen_app.entity.Book;
import tech.helen_app.request_model.AuthorRequest;
import tech.helen_app.service.AuthorService;
import tech.starter.Timer;

import java.util.NoSuchElementException;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping(path = "author")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @GetMapping("/{id}")
    public Optional<Author> getBook(@PathVariable long id) {
        return authorService.getAuthor(id);
    }

    @PostMapping
    public ResponseEntity<Author> createAuthor(@RequestBody AuthorRequest authorRequest) {

        log.info("Новый автор - {}, {}", authorRequest.getFirstname(), authorRequest.getLastname());

        try {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(authorService.createAuthor(authorRequest));
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
