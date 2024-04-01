package ru.gb.practice_spring.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.practice_spring.controllers.book.BookRequest;
import ru.gb.practice_spring.entity.Book;
import ru.gb.practice_spring.repository.BookRepository;

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
        Book newBook = new Book(bookRequest.getName());
        bookRepository.save(newBook);
        return newBook;
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
