package ru.gb.practice_spring.services;

import lombok.AllArgsConstructor;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;
import ru.gb.practice_spring.controllers.issue.IssueRequest;
import ru.gb.practice_spring.entity.Issue;
import ru.gb.practice_spring.repository.BookRepository;
import ru.gb.practice_spring.repository.IssueRepository;
import ru.gb.practice_spring.repository.ReaderRepository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.NoSuchElementException;


// 2.1 В сервис IssueService добавить проверку, что у пользователя на руках нет книг.
// * Если есть - не выдавать книгу (статус ответа - 409 Conflict)

@Service
@AllArgsConstructor
public class IssueService {

    private final BookRepository bookRepository;
    private final IssueRepository issueRepository;
    private final ReaderRepository readerRepository;


    public Issue issueIfUserHasNoBooks(IssueRequest request) {
        long readerId = request.getReaderId();
        if (readerId != issueRepository.findById(readerId).getId() || request.getReturned_at() != null) {
            return createIssue(request);
        } else {
            throw new IllegalArgumentException("У вас уже есть книга, новую взять нельзя!!!");
        }
    }


    public Issue createIssue(IssueRequest request) {
        if (bookRepository.findById(request.getBookId()) == null) {
            throw new NoSuchElementException("Не удалось найти книгу с id!" + request.getBookId());
        }

        if (readerRepository.findById(request.getReaderId()) == null) {
            throw new NoSuchElementException("Не удалось найти читателя с id!" + request.getReaderId());
        }

        Issue issue = new Issue(request.getReaderId(), request.getBookId());
        issueRepository.createIssue(issue);

        return issue;
    }

    public Issue getIssue(long id) {
        return issueRepository.findById(id);
    }

    public void setReturnedDateOfIssue(long issueId, String returned_at) {
        String dateTimeString = returned_at.split("\"")[3];
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSSS");
        LocalDateTime date = LocalDateTime.parse(dateTimeString, formatter);
        issueRepository.save(issueId, date);
    }
}
