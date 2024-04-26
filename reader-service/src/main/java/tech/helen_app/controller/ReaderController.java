package tech.helen_app.controller;


//    * 1.2 Реализовать контроллер по управлению читателями (аналогично контроллеру с книгами из 1.1)

// * GET /reader/{id} - получить чит,
// * DELETE /reader/{id} - удалить чит,
//* POST /reader - создать чит


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.helen_app.request_model.ReaderRequest;
import tech.helen_app.service.ReaderService;
import tech.helen_app.entity.Reader;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping(path = "reader")
public class ReaderController {
    @Autowired
    private ReaderService service;

    @GetMapping("{id}")
    public Optional<Reader> getReader(@PathVariable long id) {
        return service.getReader(id);
    }


    @GetMapping()
    public List<Reader> getReaders() {
        return service.getAll();
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

//    @DeleteMapping("/{id}")
//    public Optional<Reader> deleteReader(@PathVariable long id) {
//        return service.deleteReader(id);
//    }
//
////    GET /reader/{id}/issue
//
//    @GetMapping("/{id}/issue")
//    public ResponseEntity<List<Issue>> getAllIssue(@PathVariable long id) {
//        try {
//            return ResponseEntity.status(HttpStatus.ACCEPTED)
//                    .body(service.getAllIssueOfReader(id));
//        } catch (NoSuchElementException e) {
//            return ResponseEntity.noContent().build();
//        }
//    }
}
