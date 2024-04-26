package tech.helen_app.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "issues")
public class Issue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private long idReader;

    private long idBook;

    private LocalDateTime issued_at;

    private LocalDateTime returned_at;

    public Issue(long idReader, long idBook) {
        this.idBook = idBook;
        this.idReader = idReader;
        this.issued_at = LocalDateTime.now();
        returned_at = null;
    }

    public Issue(long idReader, long idBook, LocalDateTime returned_at) {
        this.idBook = idBook;
        this.idReader = idReader;
        this.issued_at = LocalDateTime.now();
        this.returned_at = returned_at;
    }
}
