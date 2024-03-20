package ru.gb.practice_spring.controllers.issue;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gb.practice_spring.entity.Issue;
import ru.gb.practice_spring.services.IssueService;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;


//GET /issue/{id} - получить описание факта выдачи
@Slf4j
@RestController
@RequestMapping("issue")
public class IssueController {
    @Autowired
    private IssueService service;

    @GetMapping("/{id}")
    public Issue getIssue(@PathVariable long id) {
        return service.getIssue(id);
    }

    @PostMapping
    public ResponseEntity<Issue> issueBook(@RequestBody IssueRequest issueRequest) {
        log.info("Поступил запрос на выдачу: readerId = {}, bookId = {}",
                issueRequest.getReaderId(), issueRequest.getBookId());

        try {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(service.createIssue(issueRequest));
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/check")
    public ResponseEntity<Issue> issueBookIfReaderHasNoBook(@RequestBody IssueRequest issueRequest) {
        log.info("Поступил запрос на выдачу: readerId = {}, bookId = {}",
                issueRequest.getReaderId(), issueRequest.getBookId());

        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(service.issueIfUserHasNoBooks(issueRequest));
        } catch (IllegalArgumentException e) {
            log.info("Уже есть книга bookId = {} у пользователя readerId = {},",
                    issueRequest.getReaderId(), issueRequest.getBookId());
            return ResponseEntity.status(409).build();

        }
    }

    //    PUT /issue/{issueId},
    @PutMapping("/{issueId}")
    public void setReturnDate(@PathVariable long issueId,
                              @RequestBody String returned_at) {
        service.setReturnedDateOfIssue(issueId, returned_at);

    }
}
