package tech.helen_app.mapper;

import tech.helen_app.model.Author;
import tech.helen_app.model.Book;
import tech.helen_app.model.Reader;

public class BookMapper {
    public Book mapBookWithAuthorInfo(Author author, Book book, Reader reader) {
        return Book.builder()
                .id(book.getId())
                .name(book.getName())
                .author(Author.builder()
                        .id(author.getId())
                        .firstname(author.getFirstname())
                        .lastname(author.getLastname())
                        .build())
                .reader(Reader.builder()
                        .id(reader.getId())
                        .name(reader.getName())
                        .build())

                .build();
    }
}
