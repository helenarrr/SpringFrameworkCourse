package tech.helen_app.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tech.helen_app.dao.ReaderRepository;
import tech.helen_app.entity.Reader;
import tech.helen_app.request_model.ReaderRequest;

import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
public class ReaderService {

    private final ReaderRepository readerRepository;

//    private final IssueRepository issueRepository;

    public Optional<Reader> getReader(long id) {
        return readerRepository.findById(id);
    }

    public Reader createReader(ReaderRequest readerRequest) {
        Reader newReader = new Reader(readerRequest.getName());
        readerRepository.save(newReader);
        return newReader;
    }

    public Optional<Reader> deleteReader(long id) {
        Optional<Reader> reader = readerRepository.findById(id);
        readerRepository.deleteById(id);
        return reader;
    }

    public List<Reader> getAll() {
        return readerRepository.findAll();
    }

//    public List<Issue> getAllIssueOfReader(long readerId) {
//        Optional<Reader> reader = readerRepository.findById(readerId);
//        return issueRepository.findIssuesByReaderId(readerId);
//    }
}
