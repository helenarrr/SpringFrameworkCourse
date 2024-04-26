package tech.helen_app.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tech.helen_app.dao.BookRepository;
import tech.helen_app.entity.Author;
import tech.helen_app.entity.Book;
import tech.helen_app.request_model.AuthorRequest;
import tech.helen_app.request_model.BookRequest;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BookService {
    private final BookRepository bookRepository;

    public Optional<Book> getBook(long id) {
        return bookRepository.findById(id);
    }

    public Book createBook(BookRequest bookRequest) {
        Book book = Book.builder()
                .name(bookRequest.getName())
                .author(Author.builder()
                        .id(bookRequest.getAuthorRequest().getId())
                        .firstname(bookRequest.getAuthorRequest().getFirstname())
                        .lastname(bookRequest.getAuthorRequest().getLastname()).build())
                .build();
        bookRepository.save(book);
        return book;
    }

    public Optional<Book> deleteBook(long id) {
        Optional<Book> book = bookRepository.findById(id);
        bookRepository.deleteById(id);
        return book;
    }

    public List<Book> findAll() {
        return bookRepository.findAll();
    }
}
