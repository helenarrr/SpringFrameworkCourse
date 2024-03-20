package ru.gb.practice_spring.repository;

import org.springframework.stereotype.Repository;
import ru.gb.practice_spring.entity.Book;

import java.util.ArrayList;
import java.util.List;


@Repository
public class BookRepository {

    private List<Book> listBooks = new ArrayList<>();

    public BookRepository() {
        listBooks.add(new Book("Война и мир"));
        listBooks.add(new Book("Мастер и маргарита"));
        listBooks.add(new Book("Маленькие женщины"));
    }

    public List<Book> getAllBooks() {
        return listBooks;
    }

    public Book findById(long id) {
        return listBooks.stream()
                .filter(e -> e.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public void deleteBookById(long id) {
        listBooks = listBooks
                .stream()
                .filter(e -> e.getId() != id)
                .toList();
    }
}
