package ru.gb.practice_spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.gb.practice_spring.entity.Reader;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Repository
public interface ReaderRepository extends JpaRepository<Reader, Long> {

}
