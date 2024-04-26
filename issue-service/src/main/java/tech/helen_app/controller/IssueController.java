package tech.helen_app.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.helen_app.dto.IssueDto;
import tech.helen_app.request_model.IssueRequest;
import tech.helen_app.service.IssueService;
import tech.helen_app.entity.Issue;

import java.util.NoSuchElementException;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping(path = "issues")
public class IssueController {
    @Autowired
    private IssueService service;

    @GetMapping("{id}")
    public Optional<IssueDto> getIssue(@PathVariable long id) {
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

//    @PutMapping("/{issueId}")
//    public void setReturnDate(@PathVariable long issueId,
//                              @RequestBody String returned_at) {
//        service.setReturnedDateOfIssue(issueId, returned_at);
//
//    }
}
