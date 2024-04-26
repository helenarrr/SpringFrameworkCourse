package tech.helen_app.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Author {
    private long id;
    private String firstname;
    private String lastname;
}
