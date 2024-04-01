package ru.gb.practice_spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.gb.practice_spring.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

}
