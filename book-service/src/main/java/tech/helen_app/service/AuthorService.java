package tech.helen_app.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tech.helen_app.dao.AuthorRepository;
import tech.helen_app.entity.Author;
import tech.helen_app.entity.Book;
import tech.helen_app.request_model.AuthorRequest;

import java.util.Optional;

@Service
@AllArgsConstructor
public class AuthorService {

    private final AuthorRepository repository;

    public Author createAuthor(AuthorRequest request) {
        Author author = new Author(request.getFirstname(), request.getLastname());
        repository.save(author);
        return author;
    }

    public Optional<Author> getAuthor(long id) {
        return repository.findById(id);
    }

}
