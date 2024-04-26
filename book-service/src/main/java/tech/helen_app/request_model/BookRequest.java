package tech.helen_app.request_model;

import lombok.Data;
import tech.helen_app.entity.Author;

@Data
public class BookRequest {

    private String name;
    private AuthorRequest authorRequest;

}
