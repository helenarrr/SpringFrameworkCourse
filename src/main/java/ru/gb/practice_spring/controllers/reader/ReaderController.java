package ru.gb.practice_spring.controllers.reader;


//    * 1.2 Реализовать контроллер по управлению читателями (аналогично контроллеру с книгами из 1.1)

// * GET /reader/{id} - получить чит,
// * DELETE /reader/{id} - удалить чит,
//* POST /reader - создать чит


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gb.practice_spring.entity.Issue;
import ru.gb.practice_spring.entity.Reader;
import ru.gb.practice_spring.repository.ReaderRepository;
import ru.gb.practice_spring.services.ReaderService;

import java.util.List;
import java.util.NoSuchElementException;

@Slf4j
@RestController
@RequestMapping(path = "reader")
public class ReaderController {
    @Autowired
    private ReaderService service;

    @Autowired
    private ReaderRepository repository;

    @GetMapping
    public List<Reader> getAllBooks() {
        return repository.getAllReaders();
    }

    @GetMapping("/{id}")
    public Reader getReader(@PathVariable long id) {
        return service.getReader(id);
    }

    @PostMapping
    public ResponseEntity<Reader> createReader(@RequestBody ReaderRequest readerRequest) {

        log.info("Записан новый читатель - {}", readerRequest.getName());

        try {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(service.createReader(readerRequest));
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public Reader deleteReader(@PathVariable long id) {
        return service.deleteReader(id);
    }

//    GET /reader/{id}/issue

    @GetMapping("/{id}/issue")
    public ResponseEntity<List<Issue>> getAllIssue(@PathVariable long id) {
        try {
            return ResponseEntity.status(HttpStatus.ACCEPTED)
                    .body(service.getAllIssueOfReader(id));
        } catch (NoSuchElementException e) {
            return ResponseEntity.noContent().build();
        }
    }
}
