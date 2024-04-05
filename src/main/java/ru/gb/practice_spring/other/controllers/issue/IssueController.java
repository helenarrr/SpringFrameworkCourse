package ru.gb.practice_spring.other.controllers.issue;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gb.practice_spring.other.controllers.entity.Issue;
import ru.gb.practice_spring.other.controllers.services.IssueService;

import java.util.NoSuchElementException;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("issue")
public class IssueController {
    @Autowired
    private IssueService service;

    @GetMapping("/{id}")
    public Optional<Issue> getIssue(@PathVariable long id) {
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

    @PutMapping("/{issueId}")
    public void setReturnDate(@PathVariable long issueId,
                              @RequestBody String returned_at) {
        service.setReturnedDateOfIssue(issueId, returned_at);

    }
}
