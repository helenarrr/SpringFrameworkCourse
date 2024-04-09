package ru.gb.practice_spring.other.controllers.student;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/")
public class StudentController {


//  * 3.1 GET /student/{id} - получить студента по ID
//* 3.2 GET /student - получить всех студентов
//* 3.3 GET /student/search?name='studentName' - получить список студентов, чье имя содержит подстроку studentName
//* 3.4 GET /group/{groupName}/student - получить всех студентов группы
//* 4. При старте приложения, в программе должно быть создано 5-10 студентов.


    StudentRepository studentRepository;

    @Autowired
    public StudentController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @GetMapping("/student/{id}")
    public Student getById(@PathVariable long id) {
        return studentRepository.getById(id);
    }

    @GetMapping("/student")
    public List<Student> getAll() {
        return studentRepository.getAll();
    }

    @GetMapping("/student/search")
    public List<Student> getByContainsName(@RequestParam String name) {
        return studentRepository.filterStudents(name);
    }

    @GetMapping("/group/{groupName}/student")
    public List<Student> getAllStudentsOfGroup(@PathVariable String groupName) {
        return studentRepository.filterStudentsForGroup(groupName);
    }
}
