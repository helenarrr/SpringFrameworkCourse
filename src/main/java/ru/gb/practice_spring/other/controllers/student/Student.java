package ru.gb.practice_spring.other.controllers.student;

import lombok.Data;

@Data
public class Student {

    long id;

    private String name;

    private String nameOfGroup;

    private static long curId = 0;

    public Student(String name, String nameOfGroup) {
        this.id = curId++;
        this.name = name;
        this.nameOfGroup = nameOfGroup;
    }
}
