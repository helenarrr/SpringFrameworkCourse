package ru.gb.practice_spring.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.practice_spring.controllers.reader.ReaderRequest;
import ru.gb.practice_spring.entity.Issue;
import ru.gb.practice_spring.entity.Reader;
import ru.gb.practice_spring.repository.IssueRepository;
import ru.gb.practice_spring.repository.ReaderRepository;

import java.util.List;
import java.util.NoSuchElementException;



@Service
@AllArgsConstructor
public class ReaderService {

    private final ReaderRepository readerRepository;

    private final IssueRepository issueRepository;

    public Reader getReader(long id) {
        Reader reader = readerRepository.findById(id);
        if (reader == null) {
            throw new NoSuchElementException("Нет читателя с id " + id);
        } else return reader;
    }

    public Reader createReader(ReaderRequest readerRequest) {
        readerRepository.getAllReaders().add(new Reader(readerRequest.getName()));
        return new Reader(readerRequest.getName());
    }

    public Reader deleteReader(long id) {
        Reader reader = readerRepository.findById(id);
        if (reader == null) {
            throw new NoSuchElementException("Нет читателя с id" + id);
        } else readerRepository.deleteReaderById(id);
        return reader;
    }

    public List<Issue> getAllIssueOfReader(long readerId) {
        Reader reader = readerRepository.findById(readerId);
        if (reader == null) {
            throw new NoSuchElementException("Нет читателя с id" + readerId);
        } else return issueRepository.returnAllIssuesOfReader(readerId);
    }
}
