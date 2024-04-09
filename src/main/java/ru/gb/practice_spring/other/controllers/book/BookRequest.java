package ru.gb.practice_spring.other.controllers.book;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

@Data
public class BookRequest {

    private String name;
}
