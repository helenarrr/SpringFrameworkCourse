package tech.helen_app.request_model;

import lombok.Data;

@Data
public class AuthorRequest {
    private long id;
    private String firstname;
    private String lastname;

}
