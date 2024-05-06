package tech.helen_app.controller;


import io.zonky.test.db.AutoConfigureEmbeddedDatabase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;
import tech.helen_app.dao.BookRepository;
import tech.helen_app.entity.Book;

import java.util.List;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@ActiveProfiles("test")
@AutoConfigureEmbeddedDatabase(provider = AutoConfigureEmbeddedDatabase.DatabaseProvider.ZONKY)
public class BookControllerTestIT {

    @Autowired
    WebTestClient webTestClient;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    BookController bookController;

    @BeforeEach
    void setUp() {

        bookRepository.deleteAll();

        Book book = new Book();
        book.setName("Книга 1");
        bookRepository.saveAndFlush(book);
    }


    @Test
    void shouldReturnAllBooks() {
        // Act
        ResponseEntity<List<Book>> listOfBooks = bookController.getAllBooks();

        List<Book> responseBody = webTestClient.get()
                .uri("books")
                .exchange()
                .expectStatus().isOk()
                .expectBody(new ParameterizedTypeReference<List<Book>>() {
                })
                .returnResult()
                .getResponseBody();

        assert responseBody != null;
        assertThat(Objects.requireNonNull(listOfBooks.getBody()).size()).isEqualTo(responseBody.size());

        responseBody.forEach(
                responseBook -> {
                    listOfBooks.getBody().forEach(
                            book -> {
                                Assertions.assertEquals(responseBook.getName(), book.getName());
                            }
                    );
                }
        );
    }
}


