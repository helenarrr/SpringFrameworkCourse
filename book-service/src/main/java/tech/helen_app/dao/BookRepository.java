package tech.helen_app.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import tech.helen_app.entity.Book;

@RepositoryRestResource(path = "books")
public interface BookRepository extends JpaRepository<Book, Long> {

}
