package ru.gb.practice_spring.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.practice_spring.controllers.issue.IssueRequest;
import ru.gb.practice_spring.entity.Issue;
import ru.gb.practice_spring.repository.BookRepository;
import ru.gb.practice_spring.repository.IssueRepository;
import ru.gb.practice_spring.repository.ReaderRepository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;


// 2.1 В сервис IssueService добавить проверку, что у пользователя на руках нет книг.
// * Если есть - не выдавать книгу (статус ответа - 409 Conflict)

@Service
@AllArgsConstructor
public class IssueService {

    private final BookRepository bookRepository;
    private final IssueRepository issueRepository;
    private final ReaderRepository readerRepository;

    public Issue createIssue(IssueRequest request) {
        bookRepository.findById(request.getBookId());
        readerRepository.findById(request.getReaderId());

        Issue issue = new Issue(request.getReaderId(), request.getBookId());
        issueRepository.save(issue);

        return issue;
    }

    public Optional<Issue> getIssue(long id) {
        return issueRepository.findById(id);
    }

    public void setReturnedDateOfIssue(long issueId, String returned_at) {
        String dateTimeString = returned_at.split("\"")[3];
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS");
        LocalDateTime date = LocalDateTime.parse(dateTimeString, formatter);
        issueRepository.saveWithReturnedDate(issueId, date);
    }
}
