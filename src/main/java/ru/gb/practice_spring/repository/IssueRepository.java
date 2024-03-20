package ru.gb.practice_spring.repository;

import org.springframework.stereotype.Repository;
import ru.gb.practice_spring.entity.Issue;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Repository
public class IssueRepository {
    private List<Issue> listIssue = new ArrayList<>();

    public void createIssue(Issue issue) {
        listIssue.add(issue);
    }

    public Issue findById(long id) {
        return listIssue.stream().filter(e -> e.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public List<Issue> returnAllIssuesOfReader(long readerId) {
        if (listIssue.isEmpty()) {
            throw new NoSuchElementException("Список выдачи пуст!");
        } else return listIssue.stream().filter(e -> e.getIdReader() == readerId).toList();
    }

    public void save(long id, LocalDateTime dateTime) {
        Issue issue = listIssue.stream().filter(e -> e.getId() == id)
                .findFirst()
                .orElse(null);

        issue.setReturned_at(dateTime);
    }
}
