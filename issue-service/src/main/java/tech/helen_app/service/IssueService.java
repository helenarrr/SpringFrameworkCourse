package tech.helen_app.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tech.helen_app.dao.IssueRepository;
import tech.helen_app.dto.IssueDto;
import tech.helen_app.entity.Issue;
import tech.helen_app.model.Author;
import tech.helen_app.model.Book;
import tech.helen_app.model.Reader;
import tech.helen_app.provider.AuthorProvider;
import tech.helen_app.provider.BookProvider;
import tech.helen_app.provider.ReaderProvider;
import tech.helen_app.request_model.IssueRequest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;


// 2.1 В сервис IssueService добавить проверку, что у пользователя на руках нет книг.
// * Если есть - не выдавать книгу (статус ответа - 409 Conflict)

@Service
@AllArgsConstructor
public class IssueService {

    private final ReaderProvider readerProvider;
    private final BookProvider bookProvider;
    private final AuthorProvider authorProvider;

    private final IssueRepository issueRepository;

    @Transactional
    public Issue createIssue(IssueRequest request) {
        Issue issue = new Issue(request.getReaderId(), request.getBookId());
        issueRepository.save(issue);

        return issue;
    }

    public Optional<IssueDto> getIssue(long id) {
        Optional<Issue> optionalIssue = issueRepository.findById(id);

        if (optionalIssue.isPresent()) {
            Issue issue = optionalIssue.get();

            Book book = bookProvider.findBookById(issue.getIdBook());
            Reader reader = readerProvider.findReaderById(issue.getIdReader());

            return Optional.ofNullable(IssueDto.builder()
                    .id(id)
                    .book(book)
                    .reader(reader)
                    .build());
        } else {
            return Optional.empty();
        }
    }
//
//    public void setReturnedDateOfIssue(long issueId, String returned_at) {
//        String dateTimeString = returned_at.split("\"")[3];
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS");
//        LocalDateTime date = LocalDateTime.parse(dateTimeString, formatter);
//        issueRepository.saveWithReturnedDate(issueId, date);
//    }
}
