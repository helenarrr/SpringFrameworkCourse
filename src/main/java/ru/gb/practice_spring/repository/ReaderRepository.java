package ru.gb.practice_spring.repository;

import org.springframework.stereotype.Repository;
import ru.gb.practice_spring.entity.Reader;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ReaderRepository {

    private List<Reader> listReaders = new ArrayList<>();

    public ReaderRepository() {
        listReaders.add(new Reader("Петя"));
        listReaders.add(new Reader("Маша"));
        listReaders.add(new Reader("Серега"));
    }


    public Reader findById(long id) {
        return listReaders.stream().filter(e -> e.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public List<Reader> getAllReaders() {
        return listReaders;
    }

    public void deleteReaderById(long id) {
        listReaders = listReaders
                .stream()
                .filter(e -> e.getId() != id)
                .toList();
    }
}
