package tech.helen_app.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "book_name")
    private String name;

    @OneToOne
    @JoinColumn(name = "author_id")
    private Author author;

    public Book(String name) {
        this.name = name;
    }

}
