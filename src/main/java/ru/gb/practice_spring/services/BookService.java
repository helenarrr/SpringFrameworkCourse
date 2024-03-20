package ru.gb.practice_spring.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.practice_spring.controllers.book.BookRequest;
import ru.gb.practice_spring.entity.Book;
import ru.gb.practice_spring.repository.BookRepository;

import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
public class BookService {
    private final BookRepository bookRepository;

    public Book getBook(long id) {
        Book book = bookRepository.findById(id);
        if (book == null) {
            throw new NoSuchElementException("Не удалось найти книгу с id!" + id);
        } else return book;
    }

    public Book createBook(BookRequest bookRequest) {
        bookRepository.getAllBooks().add(new Book(bookRequest.getName()));
        return new Book(bookRequest.getName());
    }

    public Book deleteBook(long id) {
        Book book = bookRepository.findById(id);
        if (book == null) {
            throw new NoSuchElementException("Не удалось найти книгу с id!" + id);
        }
        else bookRepository.deleteBookById(id);
        return book;
    }
}
