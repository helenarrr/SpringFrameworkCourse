package tech.helen_app.request_model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class IssueRequest {

    private long readerId;
    private long bookId;
    private LocalDateTime returned_at;

}
