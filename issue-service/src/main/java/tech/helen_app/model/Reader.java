package tech.helen_app.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Reader {
    private long id;
    private String name;

}
