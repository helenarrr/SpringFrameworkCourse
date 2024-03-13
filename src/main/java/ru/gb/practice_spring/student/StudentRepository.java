package ru.gb.practice_spring.student;


import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Component
public class StudentRepository {

    private final List<Student> students;

    public StudentRepository() {
        students = new ArrayList<>();
        students.add(new Student("Костя", "Первая"));
        students.add(new Student("Иван", "Вторая"));
        students.add(new Student("Семен", "Третья"));
        students.add(new Student("Кирилл", "Четвертая"));
        students.add(new Student("Василий", "Пятая"));
    }

    public Student getById(long id){
        return students.stream()
                .filter(e -> e.getId() == id)
                .findFirst()
                .orElse(null);
    }


    public List<Student> getAll(){
        return List.copyOf(students);
    }

    public List<Student> filterStudents(String studentName) {
        return students.stream()
                .filter(student -> student.getName().contains(studentName))
                .collect(Collectors.toList());
    }
    public List<Student> filterStudentsForGroup(String groupName) {
        return students.stream()
                .filter(student -> student.getNameOfGroup().contains(groupName))
                .collect(Collectors.toList());
    }

}
