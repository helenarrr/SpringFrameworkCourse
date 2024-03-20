package ru.gb.practice_spring.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Data
public class Issue {

    private static long genId;

    private final long id;

    private final long idReader;

    private final long idBook;

    private final LocalDateTime issued_at;

    private LocalDateTime returned_at;

    public Issue(long idReader, long idBook) {
        id = genId++;
        this.idBook = idBook;
        this.idReader = idReader;
        this.issued_at = LocalDateTime.now();
        returned_at = null;
    }

    public Issue(long idReader, long idBook, LocalDateTime returned_at) {
        id = genId++;
        this.idBook = idBook;
        this.idReader = idReader;
        this.issued_at = LocalDateTime.now();
        this.returned_at = returned_at;
    }
}
