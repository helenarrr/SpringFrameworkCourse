package tech.helen_app.dto;

import lombok.Builder;
import lombok.Data;
import tech.helen_app.model.Author;
import tech.helen_app.model.Book;
import tech.helen_app.model.Reader;

@Data
@Builder
public class IssueDto {
    private long id;
    private Book book;
    private Author author;
    private Reader reader;

}
