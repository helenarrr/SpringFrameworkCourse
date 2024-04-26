package tech.helen_app.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.helen_app.entity.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
}
