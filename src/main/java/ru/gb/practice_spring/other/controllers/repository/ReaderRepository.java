package ru.gb.practice_spring.other.controllers.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.gb.practice_spring.other.controllers.entity.Reader;

@Repository
public interface ReaderRepository extends JpaRepository<Reader, Long> {

}